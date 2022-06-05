package engine.rule.play

import engine.card.Card
import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

typealias Rule = (RuleInput) -> RuleSummary

private fun getDesiredCountRule(desiredCount: Int, ruleType: RuleType): Rule {

    fun isDesiredCount(ruleInput: RuleInput): RuleSummary {
        val isDesiredCount = ruleInput.runningTotal() == desiredCount
        return if (isDesiredCount) {
            RuleSummary(ruleType, 2, ruleInput.stack().cards() + ruleInput.card())
        } else {
            emptyRuleSummary(ruleType)
        }
    }
    return ::isDesiredCount
}

internal fun fifteen(ruleInput: RuleInput): RuleSummary {
    return getDesiredCountRule(15, RuleType.FIFTEEN)(ruleInput)
}

internal fun thirtyOne(ruleInput: RuleInput): RuleSummary {
    return getDesiredCountRule(31, RuleType.THIRTY_ONE)(ruleInput)
}

internal fun pairs(ruleInput: RuleInput): RuleSummary {

    fun choose(n: Int, k: Int): Int {
        if (k == 0) {
            return 1
        }
        return n * choose(n - 1, k - 1) / k
    }

    val ruleType = RuleType.PAIR
    val pairs = ruleInput.stack().cards().takeLastWhile { it.rank() == ruleInput.card().rank() }
    if (pairs.isEmpty()) {
        return emptyRuleSummary(ruleType)
    }
    return RuleSummary(
        ruleType,
        choose(pairs.size + 1, 2) * 2,
        pairs + ruleInput.card()
    )
}

internal fun runs(ruleInput: RuleInput): RuleSummary {

    fun findRun(stack: List<Card>, card: Card, n: Int): List<Card> {
        if (stack.size < n - 1) {
            return emptyList()
        }
        val subset = (stack.takeLast(n - 1) + card).sortedBy { c -> c.rank() }
        val groups = subset.mapIndexed { index, c -> c.rank().ordinal - index }.distinct().count()
        if (groups == 1) {
            return subset
        }
        return emptyList()
    }

    val ruleType = RuleType.RUN
    val stackSize = ruleInput.stack().cards().size
    if (stackSize < 2) {
        return emptyRuleSummary(ruleType)  // can't have a run of less than three
    }
    var longestRun = emptyList<Card>()
    for (runLength in 3..stackSize + 1) {
        val run = findRun(ruleInput.stack().cards(), ruleInput.card(), runLength)
        if (run.size > longestRun.size) {
            longestRun = run
        }
    }
    return if (longestRun.isEmpty()) {
        emptyRuleSummary(ruleType)
    } else RuleSummary(
        ruleType,
        longestRun.size,
        longestRun
    )
}
