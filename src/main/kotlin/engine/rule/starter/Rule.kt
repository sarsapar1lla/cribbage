package engine.rule.starter

import engine.card.Card
import engine.rule.RuleSummary
import engine.rule.RuleType

interface Rule {

    val ruleType: RuleType

    val points: Int

    fun apply(starterCard: Card): RuleSummary

}
