package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class PairsTest {

    private val card = Card(Suit.DIAMONDS, Rank.THREE)
    private fun ruleInput(stack: Stack): RuleInput { return RuleInput(stack, card) }

    @Test
    fun scoresPairs() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.THREE),
                Card(Suit.CLUBS, Rank.THREE)
            )
        )
        val pairs = Pairs().apply(ruleInput(stack))
        assertEquals(6, pairs)
    }

    @Test
    fun scoresNotPairs() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.THREE),
                Card(Suit.CLUBS, Rank.FOUR)
            )
        )
        val pairs = Pairs().apply(ruleInput(stack))
        assertEquals(0, pairs)
    }

}
