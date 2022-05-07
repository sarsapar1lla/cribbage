package engine.player

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RandomComputerPlayerTest {

    private val player = RandomComputerPlayer("Tester")

    val cards = setOf(
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.HEARTS, Rank.ACE),
        Card(Suit.DIAMONDS, Rank.KING)
    )

    @Test
    fun discardsTwoCards() {
        player.giveCards(cards)
        val discarded = player.discard()
        assertEquals(2, discarded.size)
        assertTrue(discarded.none { c -> player.getHand().getCards().contains(c) })
    }

    @Test
    fun selectsACard() {
        player.giveCards(cards)
        val selectedCard = player.playCard(emptySet(), 0, 31)
        assertTrue(player.getHand().getCards().contains(selectedCard))  // card is still in hand
    }

}
