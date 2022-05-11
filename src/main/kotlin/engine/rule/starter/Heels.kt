package engine.rule.starter

import engine.card.Card
import engine.card.Rank
import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

class Heels : Rule {

    override val ruleType = RuleType.HEELS

    override val points: Int = 2

    override fun apply(starterCard: Card): RuleSummary {
        if (starterCard.getRank() == Rank.JACK) {
            return RuleSummary(ruleType, points, listOf(starterCard))
        }
        return emptyRuleSummary(ruleType)
    }

}
