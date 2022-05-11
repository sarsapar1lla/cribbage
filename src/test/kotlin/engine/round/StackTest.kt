package engine.round

import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class StackTest {

    private val cards = mutableListOf(
        Card(Suit.DIAMONDS, Rank.KING),
        Card(Suit.CLUBS, Rank.QUEEN),
        Card(Suit.CLUBS, Rank.EIGHT)
    )
    private val stack = Stack(cards)

    @Test
    fun calculatesTotal() {
        val total = stack.count()
        assertThat(total).isEqualTo(28)
    }

    @Test
    fun calculatesTotalWhenStackEmpty() {
        val emptyStack = Stack()
        assertThat(emptyStack.count()).isZero
    }

    @Test
    fun addsCard() {
        val card = Card(Suit.SPADES, Rank.ACE)
        stack.addCard(card)
        assertThat(stack.cards())
            .hasSize(4)
            .contains(card)
    }

}
