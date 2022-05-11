package engine.rule.show

import engine.card.Card
import engine.card.Rank
import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

class Nobs : Rule {

    override val ruleType = RuleType.NOBS

    override val points = 1

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val starterCardSuit = ruleInput.getStarterCard().suit()
        val nobsFilter = { c: Card -> c.suit() == starterCardSuit && c.rank() == Rank.JACK}
        val hasNobs = ruleInput.getHand().getCards().any { c -> nobsFilter(c) }
        return if (hasNobs) {
            val scoringCombination = setOf(
                ruleInput.getStarterCard(),
                ruleInput.getHand().getCards().first { c -> nobsFilter(c) }
            )
            RuleSummary(ruleType, points, setOf(scoringCombination))
        } else {
            emptyRuleSummary(ruleType)
        }
    }
}
