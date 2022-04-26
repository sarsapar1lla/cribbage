package engine.rule.play

interface Rule {

    val points: Int

    fun apply(ruleInput: RuleInput): Int

}
