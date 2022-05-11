package engine.rule.play

import engine.rule.ScoreSummary

class RulesEngine {

    private val rules: List<Rule> = listOf(
        CountReached(DesiredCount.FIFTEEN),
        CountReached(DesiredCount.THIRTY_ONE),
        Pairs(),
        Runs()
    )

    fun score(ruleInput: RuleInput): ScoreSummary {
        return ScoreSummary(rules.map { r -> r.apply(ruleInput) })
    }

}
