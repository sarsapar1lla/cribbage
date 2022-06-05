package engine.rule.starter

import engine.card.Card
import engine.rule.ScoreSummary

private val rules: List<Rule> = listOf(::heels)

fun score(starterCard: Card): ScoreSummary {
    return ScoreSummary(rules.map { it(starterCard) })
}
