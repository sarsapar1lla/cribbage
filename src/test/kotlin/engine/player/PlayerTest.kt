package engine.player

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.ui.MockUI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayerTest {

    private val player = HumanPlayer("Tester", MockUI())  // using HumanPlayer instance to test concrete methods

    @Test
    fun addsPoints() {
        player.addPoints(5)
        assertEquals(5, player.getScore())
    }

    @Test
    fun capsScoreAt121() {
        player.addPoints(122)
        assertEquals(121, player.getScore())
    }

    @Test
    fun indicatesPlayerCanGo() {
        val cards = setOf(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.DIAMONDS, Rank.TWO))
        player.giveCards(cards)
        assertTrue(player.canGo(emptySet(), 30, 31))
    }

    @Test
    fun indicatesPlayerCanNotGo() {
        val cards = setOf(Card(Suit.DIAMONDS, Rank.TWO))
        player.giveCards(cards)
        assertFalse(player.canGo(emptySet(), 30, 31))
    }

    @Test
    fun indicatesPlayerCanNotGoWhenAllCardsPlayed() {
        val cards = setOf(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.DIAMONDS, Rank.TWO))
        player.giveCards(cards)
        assertFalse(player.canGo(cards, 30, 31))
    }

}
