package engine.rule.play

class RulesEngine {

    private val rules: List<Rule> = listOf(
        CountReached(15),
        CountReached(31),
        Pairs(),
        Runs()
    )

    fun score(ruleInput: RuleInput): Int {
        return rules.sumOf { r -> r.apply(ruleInput) }
    }

}
