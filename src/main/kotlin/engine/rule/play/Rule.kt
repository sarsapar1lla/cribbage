package engine.rule.play

import engine.card.Card

interface Rule {

    val points: Int

    fun apply(cards: List<Card>): Int

}
