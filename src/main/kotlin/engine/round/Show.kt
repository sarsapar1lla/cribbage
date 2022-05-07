package engine.round

import engine.Hand
import engine.card.Card
import engine.player.Player
import engine.rule.show.RuleInput
import engine.rule.show.RulesEngine
import engine.ui.UserInterface

class Show(private val rulesEngine: RulesEngine, private val ui: UserInterface) {

    private fun scorePlayerHand(player: Player, starterCard: Card): Int {
        val ruleInput = RuleInput(player.getHand(), starterCard, false)
        val points = rulesEngine.score(ruleInput)
        player.addPoints(points)
        return points
    }

    private fun scoreCrib(dealer: Player, crib: Hand, starterCard: Card): Int {
        val ruleInput = RuleInput(crib, starterCard, true)
        val points = rulesEngine.score(ruleInput)
        dealer.addPoints(points)
        return points
    }

    fun run(dealer: Player, cutter: Player, crib: Hand, starterCard: Card) {
        val cutterPoints = scorePlayerHand(cutter, starterCard)
        ui.displayHandPoints(cutter.getPlayerName(), cutterPoints, cutter.getScore())

        val dealerPoints = scorePlayerHand(dealer, starterCard)
        ui.displayHandPoints(dealer.getPlayerName(), dealerPoints, dealer.getScore())

        val cribPoints = scoreCrib(dealer, crib, starterCard)
        ui.displayHandPoints(dealer.getPlayerName(), cribPoints, dealer.getScore(), true)
    }

}
