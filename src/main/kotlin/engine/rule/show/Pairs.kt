package engine.rule.show

import engine.card.Card
import engine.rule.RuleSummary
import engine.rule.RuleType

class Pairs : Rule {

    override val ruleType = RuleType.PAIR

    override val points = 2

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val pairs = mutableSetOf<Set<Card>>()
        val uniquePairs = ruleInput.getUniqueCombinations().filter { c -> c.size == 2 }
        for (pair in uniquePairs) {
            val pairRanks = pair.map { c -> c.getRank() }
            if (pairRanks.distinct().count() == 1) {
                pairs.add(pair)
            }
        }
        return RuleSummary(ruleType, pairs.size * points, pairs)
    }
}
