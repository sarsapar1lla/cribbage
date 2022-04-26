package engine

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class HandTest {

    private val cards = setOf(
        Card(Suit.CLUBS, Rank.KING),
        Card(Suit.DIAMONDS, Rank.FOUR),
        Card(Suit.HEARTS, Rank.EIGHT),
        Card(Suit.HEARTS, Rank.NINE)
    )

    private val hand = Hand(cards)

    @Test
    fun findsCorrectNumberOfUniqueCombinations() {
        val uniqueCombinations = hand.findUniqueCombinations(Card(Suit.SPADES, Rank.JACK))
        assertEquals(uniqueCombinations.size, 31)
    }

}
