package engine.rule

class ScoreSummary(ruleSummaries: List<RuleSummary>) {

    private val sortedSummaries = ruleSummaries.sortedBy { s -> s.ruleType() }
    private val score: Int = ruleSummaries.sumOf { r -> r.points()}

    fun ruleSummaries(): List<RuleSummary> { return sortedSummaries }

    fun score(): Int { return score }

}
