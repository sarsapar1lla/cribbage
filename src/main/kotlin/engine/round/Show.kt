package engine.round

import engine.Hand
import engine.card.Card
import engine.player.Player
import engine.rule.ScoreSummary
import engine.rule.show.RuleInput
import engine.rule.show.RulesEngine
import engine.ui.UserInterface

class Show(private val rulesEngine: RulesEngine, private val ui: UserInterface) {

    private fun scorePlayerHand(player: Player, starterCard: Card): ScoreSummary {
        val ruleInput = RuleInput(player.getHand(), starterCard, false)
        val summary = rulesEngine.score(ruleInput)
        player.addPoints(summary.getScore())
        return summary
    }

    private fun scoreCrib(dealer: Player, crib: Hand, starterCard: Card): ScoreSummary {
        val ruleInput = RuleInput(crib, starterCard, true)
        val summary = rulesEngine.score(ruleInput)
        dealer.addPoints(summary.getScore())
        return summary
    }

    fun run(dealer: Player, cutter: Player, crib: Hand, starterCard: Card) {
        val cutterScoreSummary = scorePlayerHand(cutter, starterCard)
        ui.displayHandPoints(cutter.getPlayerName(), cutterScoreSummary, cutter.getScore())

        val dealerScoreSummary = scorePlayerHand(dealer, starterCard)
        ui.displayHandPoints(dealer.getPlayerName(), dealerScoreSummary, dealer.getScore())

        val cribScoreSummary = scoreCrib(dealer, crib, starterCard)
        ui.displayHandPoints(dealer.getPlayerName(), cribScoreSummary, dealer.getScore(), true)
    }

}
