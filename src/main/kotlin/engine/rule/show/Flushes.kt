package engine.rule.show

import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

class Flushes : Rule {

    override val ruleType = RuleType.FLUSH

    override val points = 1

    private val fourCardFlushPoints = 4
    private val fiveCardFlushPoints = 5

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val uniqueSuits = ruleInput.getHand().getCards().map { c -> c.suit() }.distinct()
        if (uniqueSuits.size > 1) {
            return emptyRuleSummary(ruleType)
        }
        val starterHasSameSuit = uniqueSuits.contains(ruleInput.getStarterCard().suit())
        if (starterHasSameSuit) {
            return RuleSummary(
                ruleType,
                fiveCardFlushPoints,
                setOf(ruleInput.getHand().getCards() + ruleInput.getStarterCard())
            )
        }
        if (ruleInput.isCrib()) {
            return emptyRuleSummary(ruleType)  // crib can only score a five-card flush
        }
        return RuleSummary(ruleType, fourCardFlushPoints, setOf(ruleInput.getHand().getCards()))
    }

}
