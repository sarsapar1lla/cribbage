package engine.player

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.ui.MockUI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HumanPlayerTest {

    private val mockUI = MockUI(setOf(1, 2), 1)
    private val player = HumanPlayer("Tester", mockUI)

    val cards = setOf(
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.HEARTS, Rank.ACE),
        Card(Suit.DIAMONDS, Rank.KING)
    )

    @Test
    fun discardsCorrectCards() {
        player.giveCards(cards)
        val discarded = player.discard()
        assertEquals(2, discarded.size)
        assertEquals(setOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.EIGHT)), discarded)
        assertTrue(discarded.none { c -> player.getHand().getCards().contains(c) })
    }

    @Test
    fun selectsCorrectPlayCard() {
        player.giveCards(cards)
        val selectedCard = player.playCard(emptySet(), 0, 31)
        assertEquals(Card(Suit.HEARTS, Rank.ACE), selectedCard)
        assertTrue(player.getHand().getCards().contains(selectedCard))  // card is still in hand
    }

}
