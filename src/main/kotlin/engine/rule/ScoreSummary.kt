package engine.rule

class ScoreSummary(ruleSummaries: List<RuleSummary>) {

    private val sortedSummaries = ruleSummaries.sortedBy { s -> s.getRuleType() }
    private val score: Int = ruleSummaries.sumOf { r -> r.getPoints()}

    fun getSortedSummaries(): List<RuleSummary> { return sortedSummaries }

    fun getScore(): Int { return score }

}
