package engine.rule.starter

import engine.card.Card

interface Rule {

    val points: Int

    fun apply(starterCard: Card): Int

}
