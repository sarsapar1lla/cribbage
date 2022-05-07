package engine.rule.play

class CountReached(private val desiredCount: Int) : Rule {

    override val points = 2

    override fun apply(ruleInput: RuleInput): Int {
        val isDesiredCount = ruleInput.getRunningTotal() == desiredCount
        return if (isDesiredCount) {
            points
        } else {
            0
        }
    }

}
