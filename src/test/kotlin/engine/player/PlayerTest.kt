package engine.player

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.ui.MockUI

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

internal class PlayerTest {

    private val player = HumanPlayer("Tester", MockUI())  // using HumanPlayer instance to test concrete methods

    @Test
    fun addsPoints() {
        player.addPoints(5)
        assertThat(player.score()).isEqualTo(5)
    }

    @Test
    fun throwsErrorWhenPlayerHasWon() {
        assertThatThrownBy { player.addPoints(121) }.isInstanceOf(PlayerHasWonException::class.java)
    }

    @Test
    fun indicatesPlayerCanGo() {
        val cards = setOf(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.DIAMONDS, Rank.TWO))
        player.giveCards(cards)
        assertThat(player.canGo(emptySet(), 30, 31)).isTrue
    }

    @Test
    fun indicatesPlayerCanNotGo() {
        val cards = setOf(Card(Suit.DIAMONDS, Rank.TWO))
        player.giveCards(cards)
        assertThat(player.canGo(emptySet(), 30, 31)).isFalse
    }

    @Test
    fun indicatesPlayerCanNotGoWhenAllCardsPlayed() {
        val cards = setOf(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.DIAMONDS, Rank.TWO))
        player.giveCards(cards)
        assertThat(player.canGo(cards, 30, 31)).isFalse
    }

}
