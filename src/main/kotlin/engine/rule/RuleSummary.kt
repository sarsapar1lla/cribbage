package engine.rule

import engine.card.Card

class RuleSummary(private val ruleType: RuleType, private val points: Int, scoringCombinations: Set<Set<Card>>) {

    private val sortedCombinations: List<List<Card>>

    init {
        val combinationOrder = compareBy<Card>({c -> c.rank()}, { c -> c.suit() })
        sortedCombinations = scoringCombinations
            .map { combination -> combination.sortedWith(combinationOrder) }  // sort each combination by rank and suit
            .sortedBy { it.size }  // then arrange smallest to largest
    }

    constructor(
        ruleType: RuleType,
        points: Int,
        scoringCombinations: List<Card>
    ): this(ruleType, points, setOf(scoringCombinations.toSet()))

    fun ruleType(): RuleType { return ruleType }

    fun points(): Int { return points }

    fun scoringCombinations(): List<List<Card>> { return sortedCombinations }

}

fun emptyRuleSummary(ruleType: RuleType): RuleSummary {
    return RuleSummary(ruleType, 0, emptySet())
}
