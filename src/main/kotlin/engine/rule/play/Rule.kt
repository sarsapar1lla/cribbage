package engine.rule.play

import engine.rule.RuleSummary
import engine.rule.RuleType

interface Rule {

    val ruleType: RuleType

    val points: Int

    fun apply(ruleInput: RuleInput): RuleSummary

}
