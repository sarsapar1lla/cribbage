package engine

import engine.card.Card
import engine.player.Player
import engine.rule.show.RuleInput as ShowRuleInput
import engine.rule.show.RulesEngine as ShowRulesEngine
import engine.rule.play.RuleInput as PlayRuleInput
import engine.rule.play.RulesEngine as PlayRulesEngine
import engine.ui.UserInterface
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.coroutineScope

class Round(
    private val playRulesEngine: PlayRulesEngine,
    private val showRulesEngine: ShowRulesEngine,
    private val ui: UserInterface
    ) {

    private val deck = Deck()

    private suspend fun promptPlayersToDiscard(dealer: Player, cutter: Player) = coroutineScope {
        val dealerDiscards = async { dealer.discard() }
        val cutterDiscards = async { cutter.discard() }
        dealerDiscards.await() + cutterDiscards.await()
    }

    private fun getStarterCard(deck: Deck): Card {
        return deck.dealOne()
    }

    private fun runPlay(dealer: Player, cutter: Player) {

    }

    private fun scorePlayerHand(player: Player, starterCard: Card): Int {
        val ruleInput = ShowRuleInput(player.getHand(), starterCard, false)
        val points = showRulesEngine.score(ruleInput)
        player.addPoints(points)
        return points
    }

    private fun scoreCrib(dealer: Player, crib: Hand, starterCard: Card): Int {
        val ruleInput = ShowRuleInput(crib, starterCard, true)
        val points = showRulesEngine.score(ruleInput)
        dealer.addPoints(points)
        return points
    }

    private fun runShow(dealer: Player, cutter: Player, crib: Hand, starterCard: Card) {
        val cutterPoints = scorePlayerHand(cutter, starterCard)
        ui.displayPoints(cutter.getPlayerName(), cutterPoints, cutter.getScore())

        val dealerPoints = scorePlayerHand(dealer, starterCard)
        ui.displayPoints(dealer.getPlayerName(), dealerPoints, dealer.getScore())

        val cribPoints = scoreCrib(dealer, crib, starterCard)
        ui.displayPoints(dealer.getPlayerName(), cribPoints, dealer.getScore())
    }

    fun play(dealer: Player, cutter: Player) {
        val (cutterCards, dealerCards) = deck.deal()
        dealer.giveCards(dealerCards)
        cutter.giveCards(cutterCards)

        // Running discard methods for both players simultaneously to save time
        val crib = runBlocking {
            val cribCards = promptPlayersToDiscard(dealer, cutter)
            Hand(cribCards.toMutableSet())
        }

        val starterCard = getStarterCard(deck)
        runPlay(dealer, cutter)
        runShow(dealer, cutter, crib, starterCard)

    }

}
