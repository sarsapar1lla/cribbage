package engine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DeckTest {

    private val deck = Deck(false)

    @Test
    fun dealsTwoHandsOfSixCards() {
        val (cutterCards, dealerCards) = deck.deal()
        assertEquals(6, cutterCards.size)
        assertEquals(6, dealerCards.size)
    }

    @Test
    fun dealtCardsMatch() {
        val (cutterCards, dealerCards) = deck.deal()
        val cards = cutterCards + dealerCards
        assertEquals(cards, deck.getDealtCards().toSet())
    }

    @Test
    fun remainingCardsAreCorrect() {
        val (cutterCards, dealerCards) = deck.deal()
        val cards = cutterCards + dealerCards
        assertEquals(40, deck.getRemainingCards().size)
        assertTrue(cards.none { c -> deck.getRemainingCards().contains(c) })
    }

}
