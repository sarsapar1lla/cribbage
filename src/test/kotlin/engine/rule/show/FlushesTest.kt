package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class FlushesTest {

    private val cards = mutableSetOf(
        Card(Suit.DIAMONDS, Rank.NINE),
        Card(Suit.DIAMONDS, Rank.TWO),
        Card(Suit.DIAMONDS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.KING)
    )
    private val hand = Hand(cards)

    private fun ruleInput(starterCard: Card, isCrib: Boolean = false): RuleInput {
        return RuleInput(hand, starterCard, isCrib)
    }

    @Test
    fun countsFourCardFlush() {
        val ruleInput = ruleInput(Card(Suit.CLUBS, Rank.THREE))
        val flushes = Flushes().apply(ruleInput)
        assertEquals(4, flushes)
    }

    @Test
    fun countsFiveCardFlush() {
        val ruleInput = ruleInput(Card(Suit.DIAMONDS, Rank.THREE))
        val flushes = Flushes().apply(ruleInput)
        assertEquals(5, flushes)
    }

    @Test
    fun countsFiveCardFlushWhenCrib() {
        val ruleInput = ruleInput(Card(Suit.DIAMONDS, Rank.THREE))
        val flushes = Flushes().apply(ruleInput)
        assertEquals(5, flushes)
    }

    @Test
    fun doesNotCountFourCardFlushWhenCrib() {
        val ruleInput = ruleInput(Card(Suit.HEARTS, Rank.THREE), true)
        val flushes = Flushes().apply(ruleInput)
        assertEquals(0, flushes)
    }

}
