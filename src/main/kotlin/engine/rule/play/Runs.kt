package engine.rule.play

import engine.card.Card

class Runs : Rule {

    override val points = 1

    private fun findRun(stack: List<Card>, card: Card, n: Int): Int {
        if (stack.size < n - 1) {
            return 0
        }
        val subset = (stack.takeLast(n - 1) + card).sortedBy { c -> c.getRank() }
        val groups = subset.mapIndexed { index, c -> c.getRank().ordinal - index }.distinct().count()
        if (groups == 1) {
            return subset.size
        }
        return 0
    }

    override fun apply(ruleInput: RuleInput): Int {
        val stackSize = ruleInput.getStack().getCards().size
        if (stackSize < 2) {
            return 0  // can't have a run of less than three
        }
        var longestRun = 0
        for (runLength in 3..stackSize + 1) {
            val run = findRun(ruleInput.getStack().getCards(), ruleInput.getCard(), runLength)
            if (run > longestRun) {
                longestRun = run
            }
        }
        return longestRun * points
    }
}
