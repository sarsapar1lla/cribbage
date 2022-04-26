package engine.rule.show

class RulesEngine {

    private val rules: List<Rule> = listOf(
        Fifteens(),
        Pairs(),
        Runs(),
        Flushes(),
        Nobs()
    )

    fun score(ruleInput: RuleInput): Int {
        return rules.sumOf { r -> r.apply(ruleInput) }
    }

}
