package engine.rule.play

class CountReached(private val desiredCount: Int) : Rule {

    override val points = 2

    private val maximumCount = 31

    override fun apply(ruleInput: RuleInput): Int {
        if (ruleInput.getRunningTotal() > maximumCount) {
            throw MaximumCountExceededException("Stack total cannot exceed 31.")
        }
        val isDesiredCount = ruleInput.getRunningTotal() == desiredCount
        return if (isDesiredCount) {
            points
        } else {
            0
        }
    }

}
