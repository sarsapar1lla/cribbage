package engine.rule.play

import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

enum class DesiredCount(private val count: Int) {
    FIFTEEN(15),
    THIRTY_ONE(31);

    fun getCount(): Int { return count }
}

class CountReached(private val desiredCount: DesiredCount) : Rule {

    override val ruleType: RuleType = if (desiredCount == DesiredCount.FIFTEEN) RuleType.FIFTEEN else RuleType.THIRTY_ONE

    override val points = 2

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val isDesiredCount = ruleInput.getRunningTotal() == desiredCount.getCount()
        return if (isDesiredCount) {
            RuleSummary(ruleType, points, ruleInput.getStack().getCards() + ruleInput.getCard())
        } else {
            emptyRuleSummary(ruleType)
        }
    }

}
