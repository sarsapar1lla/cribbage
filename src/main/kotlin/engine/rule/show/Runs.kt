package engine.rule.show

class Runs : Rule {

    override val points = 1

    override fun apply(ruleInput: RuleInput): Int {
        val runs = mutableListOf<Int>()
        val possibleRuns = ruleInput.getUniqueCombinations().filter { combination -> combination.size >= 3 }
        for (possibleRun in possibleRuns.sortedByDescending { r -> r.size }) {
            val groups = possibleRun.sortedBy {
                    card -> card.getRank()
            }.mapIndexed {
                    index, card -> card.getRank().ordinal - index
            }.distinct().count()
            if (groups == 1) {
                runs.add(possibleRun.size)
                if (possibleRun.size == 5) {
                    break  // if there is a run of five cards, then there can't be a longer run to look for
                }
            }
        }
        val maxRunLength = runs.maxOrNull() ?: 0

        if (maxRunLength == 0) {
            return 0
        }
        return runs.count { r -> r == maxRunLength } * maxRunLength * points
    }
}
