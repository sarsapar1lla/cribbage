package engine.rule.show

interface Rule {

    val points: Int

    fun apply(ruleInput: RuleInput): Int

}
