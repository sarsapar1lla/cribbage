package engine.rule.starter

import engine.card.Card
import engine.rule.ScoreSummary

class RulesEngine {

    private val rules: List<Rule> = listOf(
        Heels()
    )

    fun score(starterCard: Card): ScoreSummary {
        return ScoreSummary(rules.map { r -> r.apply(starterCard) })
    }

}
