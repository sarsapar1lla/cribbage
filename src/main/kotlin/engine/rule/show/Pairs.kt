package engine.rule.show

class Pairs : Rule {

    override val points = 2

    override fun apply(ruleInput: RuleInput): Int {
        var pairs = 0
        val uniquePairs = ruleInput.getUniqueCombinations().filter { c -> c.size == 2 }
        for (pair in uniquePairs) {
            val pairRanks = pair.map { c -> c.getRank() }
            if (pairRanks.distinct().count() == 1) {
                pairs += 1
            }
        }
        return pairs * points
    }
}
