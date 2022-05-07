package engine.round

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.round.Stack
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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
        assertEquals(28, total)
    }

    @Test
    fun calculatesTotalWhenStackEmpty() {
        val emptyStack = Stack()
        assertEquals(0, emptyStack.count())
    }

    @Test
    fun addsCard() {
        val card = Card(Suit.SPADES, Rank.ACE)
        stack.addCard(card)
        assertEquals(4, stack.getCards().size)
        assertTrue(stack.getCards().contains(card))
    }

}
