package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Suit
import engine.card.Rank
import engine.rule.show.Pairs
import engine.rule.show.RuleInput

import kotlin.test.Test
import kotlin.test.assertEquals

class PairsTest {

    private val cards = setOf(
        Card(Suit.SPADES, Rank.EIGHT),
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.KING)
    )
    private val hand = Hand(cards)
    private val starterCard = Card(Suit.CLUBS, Rank.KING)
    private val ruleInput = RuleInput(hand, starterCard)

    @Test
    fun countsCorrectNumberOfPairs() {
        val pairs = Pairs().apply(ruleInput)
        assertEquals(8, pairs)
    }

}
