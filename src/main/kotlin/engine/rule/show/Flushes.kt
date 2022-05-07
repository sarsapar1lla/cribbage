package engine.rule.show

import engine.rule.RuleSummary
import engine.rule.RuleType

class Flushes : Rule {

    override val ruleType = RuleType.FLUSH

    override val points = 1

    private val fourCardFlushPoints = 4
    private val fiveCardFlushPoints = 5

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val uniqueSuits = ruleInput.getHand().getCards().map { c -> c.getSuit() }.distinct()
        if (uniqueSuits.size > 1) {
            return RuleSummary(ruleType, 0, emptySet())
        }
        val starterHasSameSuit = uniqueSuits.contains(ruleInput.getStarterCard().getSuit())
        if (starterHasSameSuit) {
            return RuleSummary(
                ruleType,
                fiveCardFlushPoints,
                setOf(ruleInput.getHand().getCards() + ruleInput.getStarterCard())
            )
        }
        if (ruleInput.isCrib()) {
            return RuleSummary(ruleType, 0, emptySet())  // crib can only score a five-card flush
        }
        return RuleSummary(ruleType, fourCardFlushPoints, setOf(ruleInput.getHand().getCards()))
    }

}
