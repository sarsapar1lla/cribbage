package engine.rule.starter

import engine.card.Card

class RulesEngine {

    private val rules: List<Rule> = listOf(
        Heels()
    )

    fun score(starterCard: Card): Int {
        return rules.sumOf { r -> r.apply(starterCard) }
    }

}
