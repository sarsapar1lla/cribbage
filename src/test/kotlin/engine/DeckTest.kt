package engine

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class DeckTest {

    private val deck = Deck(false)

    @Test
    fun has52Cards() {
        assertThat(deck.getRemainingCards()).hasSize(52)
    }

    @Test
    fun allCardsAreUnique() {
        assertThat(deck.getRemainingCards().distinct()).hasSize(52)
    }

    @Test
    fun dealsTwoHandsOfSixCards() {
        val (cutterCards, dealerCards) = deck.deal()
        assertThat(cutterCards).hasSize(6)
        assertThat(dealerCards).hasSize(6)
    }

    @Test
    fun dealtCardsMatch() {
        val (cutterCards, dealerCards) = deck.deal()
        val cards = cutterCards + dealerCards
        assertThat(deck.getDealtCards().toSet()).isEqualTo(cards)
    }

    @Test
    fun remainingCardsAreCorrect() {
        val (cutterCards, dealerCards) = deck.deal()
        val cards = cutterCards + dealerCards
        assertThat(deck.getRemainingCards()).hasSize(40)
        assertThat(cards).noneMatch { c -> deck.getRemainingCards().contains(c) }
    }

}
