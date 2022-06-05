package engine.rule.play

import engine.rule.ScoreSummary

private val rules: List<Rule> = listOf(
    ::fifteen,
    ::thirtyOne,
    ::pairs,
    ::runs
)

fun score(ruleInput: RuleInput): ScoreSummary {
    return ScoreSummary(rules.map { it(ruleInput) })
}
