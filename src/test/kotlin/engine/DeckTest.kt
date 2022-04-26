package engine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DeckTest {

    private val deck = Deck(false)

    @Test
    fun dealsTwoHandsOfSixCards() {
        val (cutterHand, dealerHand) = deck.deal()
        assertEquals(6, cutterHand.getCards().size)
        assertEquals(6, dealerHand.getCards().size)
    }

    @Test
    fun dealtCardsMatch() {
        val (cutterHand, dealerHand) = deck.deal()
        val cards = cutterHand.getCards() + dealerHand.getCards()
        assertEquals(cards, deck.getDealtCards().toSet())
    }

    @Test
    fun remainingCardsAreCorrect() {
        val (cutterHand, dealerHand) = deck.deal()
        val cards = cutterHand.getCards() + dealerHand.getCards()
        assertEquals(40, deck.getRemainingCards().size)
        assertTrue(cards.none { c -> deck.getRemainingCards().contains(c) })
    }

}
