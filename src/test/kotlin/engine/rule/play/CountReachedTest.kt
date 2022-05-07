package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class CountReachedTest {

    private val fifteenStack = Stack(
        mutableListOf(
            Card(Suit.DIAMONDS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR)
        )
    )
    private fun fifteenRuleInput(card: Card): RuleInput { return RuleInput(fifteenStack, card) }

    private val thirtyOneStack = Stack(
        mutableListOf(
            Card(Suit.DIAMONDS, Rank.TEN),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.EIGHT)
        )
    )

    private fun thirtyOneRuleInput(card: Card): RuleInput { return RuleInput(thirtyOneStack, card) }

    @Test
    fun scoresFifteen() {
        val card = Card(Suit.HEARTS, Rank.EIGHT)
        val fifteen = CountReached(15).apply(fifteenRuleInput(card))
        assertEquals(2, fifteen)
    }

    @Test
    fun scoresNotFifteen() {
        val card = Card(Suit.HEARTS, Rank.NINE)
        val fifteen = CountReached(15).apply(fifteenRuleInput(card))
        assertEquals(0, fifteen)
    }

    @Test
    fun scoresThirtyOne() {
        val card = Card(Suit.HEARTS, Rank.THREE)
        val thirtyOne = CountReached(31).apply(thirtyOneRuleInput(card))
        assertEquals(2, thirtyOne)
    }

    @Test
    fun scoresNotThirtyOne() {
        val card = Card(Suit.HEARTS, Rank.ACE)
        val thirtyOne = CountReached(31).apply(thirtyOneRuleInput(card))
        assertEquals(0, thirtyOne)
    }

}
