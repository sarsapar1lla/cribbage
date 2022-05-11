package engine.player

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.ui.MockUI

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class HumanPlayerTest {

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
        assertThat(discarded)
            .hasSize(2)
            .isEqualTo(setOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.CLUBS, Rank.EIGHT)))
        assertThat(discarded)
            .describedAs("Discards are no longer in player's hand")
            .noneMatch { c -> player.hand().getCards().contains(c) }
    }

    @Test
    fun selectsCorrectPlayCard() {
        player.giveCards(cards)
        val selectedCard = player.playCard(emptySet(), 0, 31)
        assertThat(selectedCard).isEqualTo(Card(Suit.HEARTS, Rank.ACE))
        assertThat(selectedCard)
            .describedAs("Selected card is still in player's hand")
            .isIn(player.hand().getCards())
    }

}
