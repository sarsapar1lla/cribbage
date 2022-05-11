package engine.player

import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class RandomComputerPlayerTest {

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
        assertThat(discarded)
            .hasSize(2)
            .noneMatch { c -> player.hand().getCards().contains(c) }
    }

    @Test
    fun selectsACard() {
        player.giveCards(cards)
        val selectedCard = player.playCard(emptySet(), 0, 31)
        assertThat(selectedCard).isIn(player.hand().getCards())
    }

}
