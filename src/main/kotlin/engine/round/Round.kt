package engine.round

import engine.Deck
import engine.Hand
import engine.card.Card
import engine.player.Player
import engine.player.Players
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

    private fun dealStarterCard(deck: Deck): Card {
        return deck.dealOne()
    }

    fun play(players: Players) {
        val (cutterCards, dealerCards) = deck.deal()
        players.dealer.giveCards(dealerCards)
        players.cutter.giveCards(cutterCards)

        ui.displayMessage("${players.dealer.playerName()} is the dealer this round")

        // Running discard methods for both players simultaneously to save time
        val crib = runBlocking {
            val cribCards = promptPlayersToDiscard(players.dealer, players.cutter)
            Hand(cribCards.toMutableSet())
        }

        val starterCard = dealStarterCard(deck)
        ui.displayMessage("${players.dealer.playerName()} drew $starterCard as the starterCard!")
        starter.run(starterCard, players.dealer)
        play.run(players.dealer, players.cutter)
        show.run(players.dealer, players.cutter, crib, starterCard)

    }

}
