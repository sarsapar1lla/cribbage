package engine.rule.show

import engine.rule.ScoreSummary

class RulesEngine {

    private val rules: List<Rule> = listOf(
        Fifteens(),
        Pairs(),
        Runs(),
        Flushes(),
        Nobs()
    )

    fun score(ruleInput: RuleInput): ScoreSummary {
        return ScoreSummary(rules.map { it.apply(ruleInput) })
    }

}
