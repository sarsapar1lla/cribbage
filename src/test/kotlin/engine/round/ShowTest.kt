package engine.round

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.player.PredictablePlayer
import engine.ui.MockUI

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class ShowTest {

    private val ui = MockUI()

    private val show = Show(ui)

    @Test
    fun runsShowCorrectly() {
        val player1 = PredictablePlayer(
            listOf(
                Card(Suit.CLUBS, Rank.ACE),
                Card(Suit.DIAMONDS, Rank.FOUR),
                Card(Suit.SPADES, Rank.TEN),
                Card(Suit.CLUBS, Rank.TEN)
            ),
            "Player1"
        )
        val player2 = PredictablePlayer(
            listOf(
                Card(Suit.SPADES, Rank.ACE),
                Card(Suit.HEARTS, Rank.FOUR),
                Card(Suit.SPADES, Rank.NINE),
                Card(Suit.CLUBS, Rank.QUEEN)
            ),
            "Player2"
        )
        val crib = Hand(
            mutableSetOf(
                Card(Suit.HEARTS, Rank.JACK),
                Card(Suit.HEARTS, Rank.FIVE),
                Card(Suit.DIAMONDS, Rank.TWO),
                Card(Suit.DIAMONDS, Rank.SEVEN)
            )
        )
        val startedCard = Card(Suit.CLUBS, Rank.NINE)

        show.run(player1, player2, crib, startedCard)

        assertThat(player1.score()).isEqualTo(8)
        assertThat(player2.score()).isEqualTo(4)

    }

}
