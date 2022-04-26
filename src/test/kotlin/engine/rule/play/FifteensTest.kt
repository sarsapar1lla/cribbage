package engine.rule.play

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class FifteensTest {

    private val stack = listOf(
        Card(Suit.DIAMONDS, Rank.THREE),
        Card(Suit.CLUBS, Rank.FOUR)
    )
    private fun ruleInput(card: Card): RuleInput { return RuleInput(stack, card) }

    @Test
    fun scoresFifteen() {
        val card = Card(Suit.HEARTS, Rank.EIGHT)
        val fifteens = Fifteens().apply(ruleInput(card))
        assertEquals(2, fifteens)
    }

    @Test
    fun scoresNotFifteen() {
        val card = Card(Suit.HEARTS, Rank.NINE)
        val fifteens = Fifteens().apply(ruleInput(card))
        assertEquals(0, fifteens)
    }

}
