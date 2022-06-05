package engine.round

import engine.Hand
import engine.card.Card
import engine.player.Player
import engine.rule.ScoreSummary
import engine.rule.show.RuleInput
import engine.rule.show.score
import engine.ui.UserInterface

class Show(private val ui: UserInterface) {

    private fun scorePlayerHand(player: Player, starterCard: Card): ScoreSummary {
        val ruleInput = RuleInput(player.hand(), starterCard, false)
        val summary = score(ruleInput)
        player.addPoints(summary.score())
        return summary
    }

    private fun scoreCrib(dealer: Player, crib: Hand, starterCard: Card): ScoreSummary {
        val ruleInput = RuleInput(crib, starterCard, true)
        val summary = score(ruleInput)
        dealer.addPoints(summary.score())
        return summary
    }

    fun run(dealer: Player, cutter: Player, crib: Hand, starterCard: Card) {
        val cutterScoreSummary = scorePlayerHand(cutter, starterCard)
        ui.displayHandPoints(cutter.playerName(), cutterScoreSummary, cutter.score())

        val dealerScoreSummary = scorePlayerHand(dealer, starterCard)
        ui.displayHandPoints(dealer.playerName(), dealerScoreSummary, dealer.score())

        val cribScoreSummary = scoreCrib(dealer, crib, starterCard)
        ui.displayHandPoints(dealer.playerName(), cribScoreSummary, dealer.score(), true)
    }

}
