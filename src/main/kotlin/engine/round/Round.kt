package engine.round

import engine.Deck
import engine.Hand
import engine.card.Card
import engine.player.Player
import engine.rule.RulesEngine
import engine.ui.UserInterface
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.coroutineScope

class Round(rulesEngine: RulesEngine, private val ui: UserInterface, private val deck: Deck = Deck()) {

    private val starter = Starter(rulesEngine.getStarterRulesEngine(), ui)
    private val play = Play(rulesEngine.getPlayRulesEngine(), ui)
    private val show = Show(rulesEngine.getShowRulesEngine(), ui)

    private suspend fun promptPlayersToDiscard(dealer: Player, cutter: Player) = coroutineScope {
        val dealerDiscards = async { dealer.discard() }
        val cutterDiscards = async { cutter.discard() }
        dealerDiscards.await() + cutterDiscards.await()
    }

    private fun getStarterCard(deck: Deck): Card {
        return deck.dealOne()
    }

    fun play(dealer: Player, cutter: Player) {
        val (cutterCards, dealerCards) = deck.deal()
        dealer.giveCards(dealerCards)
        cutter.giveCards(cutterCards)

        ui.displayMessage("${dealer.getPlayerName()} is the dealer this round")

        // Running discard methods for both players simultaneously to save time
        val crib = runBlocking {
            val cribCards = promptPlayersToDiscard(dealer, cutter)
            Hand(cribCards.toMutableSet())
        }

        val starterCard = getStarterCard(deck)
        ui.displayMessage("${dealer.getPlayerName()} drew $starterCard as the starterCard!")
        starter.run(starterCard, dealer)
        play.run(dealer, cutter)
        show.run(dealer, cutter, crib, starterCard)

    }

}
