package engine.rule

import engine.card.Card

class RuleSummary(private val ruleType: RuleType, private val points: Int, scoringCombinations: Set<Set<Card>>) {

    private val sortedCombinations: List<List<Card>>

    init {
        val combinationOrder = compareBy<Card>({c -> c.getRank()}, {c -> c.getSuit() })
        sortedCombinations = scoringCombinations
            .map { combination -> combination.sortedWith(combinationOrder) }  // sort each combination by rank and suit
            .sortedBy { it.size }  // then arrange smallest to largest
    }

    constructor(
        ruleType: RuleType,
        points: Int,
        scoringCombinations: List<Card>
    ): this(ruleType, points, setOf(scoringCombinations.toSet()))

    fun getRuleType(): RuleType { return ruleType }

    fun getPoints(): Int { return points }

    fun getScoringCombinations(): List<List<Card>> { return sortedCombinations }

}

fun emptyRuleSummary(ruleType: RuleType): RuleSummary {
    return RuleSummary(ruleType, 0, emptySet())
}
