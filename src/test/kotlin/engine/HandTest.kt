package engine

import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class HandTest {

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
        assertThat(uniqueCombinations).hasSize(31)
    }

    @Test
    fun removesCards() {
        val cardsToRemove = setOf(
            Card(Suit.CLUBS, Rank.KING),
            Card(Suit.DIAMONDS, Rank.FOUR)
        )
        hand.removeCards(cardsToRemove)
        assertThat(hand.getCards()).hasSize(2)
        assertThat(cardsToRemove).noneMatch { c -> hand.getCards().contains(c) }
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
        assertThat(sortedCards).isEqualTo(expectedSort)
    }

    @Test
    fun replacesCards() {
        val newCards = setOf(
            Card(Suit.SPADES, Rank.ACE)
        )
        hand.replaceCards(newCards)
        assertThat(hand.getCards()).isEqualTo(newCards)
    }

}
