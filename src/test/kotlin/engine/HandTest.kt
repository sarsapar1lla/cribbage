package engine

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HandTest {

    private val cards = mutableSetOf(
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

    @Test
    fun removesCards() {
        val cardsToRemove = setOf(
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.FOUR)
        )
        hand.removeCards(cardsToRemove)
        assertEquals(2, hand.getCards().size)
        assertTrue(cardsToRemove.none { c -> hand.getCards().contains(c) })
    }

    @Test
    fun sortsCards() {
        val expectedSort = listOf(
            Card(Suit.HEARTS, Rank.EIGHT),
            Card(Suit.HEARTS, Rank.NINE),
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.FOUR)
        )
        val sortedCards = hand.getSortedCards()
        assertEquals(expectedSort, sortedCards)
    }

    @Test
    fun replacesCards() {
        val newCards = setOf(
            Card(Suit.SPADES, Rank.ACE)
        )
        hand.replaceCards(newCards)
        assertEquals(newCards, hand.getCards())
    }

}
