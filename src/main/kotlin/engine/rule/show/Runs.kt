package engine.rule.show

import engine.card.Card
import engine.rule.RuleSummary
import engine.rule.RuleType

class Runs : Rule {

    override val ruleType = RuleType.RUN

    override val points = 1

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val runs = mutableListOf<Set<Card>>()
        val possibleRuns = ruleInput.getUniqueCombinations().filter { combination -> combination.size >= 3 }
        for (possibleRun in possibleRuns.sortedByDescending { r -> r.size }) {
            val groups = possibleRun.sortedBy {
                    card -> card.getRank()
            }.mapIndexed {
                    index, card -> card.getRank().ordinal - index
            }.distinct().count()
            if (groups == 1) {
                runs.add(possibleRun)
                if (possibleRun.size == 5) {
                    break  // if there is a run of five cards, then there can't be a longer run to look for
                }
            }
        }
        val maxRunLength = runs.maxOfOrNull { it.size } ?: 0

        if (maxRunLength == 0) {
            return RuleSummary(ruleType, 0, emptySet())
        }
        return RuleSummary(
            ruleType,
            runs.count { it.size == maxRunLength } * maxRunLength * points,
            runs.filter { it.size == maxRunLength }.toSet()

        )
    }
}
