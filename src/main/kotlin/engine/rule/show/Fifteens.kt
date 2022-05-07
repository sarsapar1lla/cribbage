package engine.rule.show

import engine.rule.RuleSummary
import engine.rule.RuleType

class Fifteens : Rule {

    override val ruleType = RuleType.FIFTEEN

    override val points = 2

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val fifteens = ruleInput.getUniqueCombinations().filter { it.sumOf { c -> c.getCardValue() } == 15 }.toSet()
        return RuleSummary(ruleType, fifteens.size * points, fifteens)
    }
}
