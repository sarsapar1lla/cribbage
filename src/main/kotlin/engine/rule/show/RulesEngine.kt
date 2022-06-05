package engine.rule.show

import engine.rule.ScoreSummary

private val rules: List<Rule> = listOf(
    ::fifteens,
    ::pairs,
    ::runs,
    ::flushes,
    ::nobs
)

fun score(ruleInput: RuleInput): ScoreSummary {
    return ScoreSummary(rules.map { it(ruleInput) })
}
