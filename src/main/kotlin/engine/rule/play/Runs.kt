package engine.rule.play

import engine.card.Card
import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

class Runs : Rule {

    override val ruleType = RuleType.RUN

    override val points = 1

    private fun findRun(stack: List<Card>, card: Card, n: Int): List<Card> {
        if (stack.size < n - 1) {
            return emptyList()
        }
        val subset = (stack.takeLast(n - 1) + card).sortedBy { c -> c.getRank() }
        val groups = subset.mapIndexed { index, c -> c.getRank().ordinal - index }.distinct().count()
        if (groups == 1) {
            return subset
        }
        return emptyList()
    }

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val stackSize = ruleInput.getStack().getCards().size
        if (stackSize < 2) {
            return emptyRuleSummary(ruleType)  // can't have a run of less than three
        }
        var longestRun = emptyList<Card>()
        for (runLength in 3..stackSize + 1) {
            val run = findRun(ruleInput.getStack().getCards(), ruleInput.getCard(), runLength)
            if (run.size > longestRun.size) {
                longestRun = run
            }
        }
        return if (longestRun.isEmpty()) {
            emptyRuleSummary(ruleType)
        } else RuleSummary(
            ruleType,
            longestRun.size * points,
            longestRun
        )
    }
}
