package engine.round

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.player.PredictablePlayer
import engine.rule.play.RulesEngine
import engine.ui.MockUI

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class PlayTest {

    private val rulesEngine = RulesEngine()
    private val ui = MockUI()

    private val play = Play(rulesEngine, ui)

    @Test
    fun scoresPlayedCardCorrectly() {
        val cards = listOf(Card(Suit.DIAMONDS, Rank.KING))
        val player = PredictablePlayer(cards)
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.ACE),
                Card(Suit.CLUBS, Rank.TWO),
                Card(Suit.SPADES, Rank.KING)
            )
        )
        play.playCard(player, stack)
        assertThat(player.getScore()).isEqualTo(2)
        assertThat(cards.first())
            .isIn(stack.getCards())
            .isIn(play.getCardsPlayed())
    }

    @Test
    fun runsPlayCorrectly() {
        val player1 = PredictablePlayer(
            listOf(
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.SPADES, Rank.THREE),
                Card(Suit.CLUBS, Rank.FIVE)
            ),
            "Player1"
        )
        val player2 = PredictablePlayer(
            listOf(
                Card(Suit.HEARTS, Rank.TWO),
                Card(Suit.DIAMONDS, Rank.FOUR)
            ),
            "Player2"
        )

        play.run(player1, player2)

        assertThat(player1.getScore()).isEqualTo(11)
        assertThat(player2.getScore()).isEqualTo(4)

    }

    @Test
    fun runsPlayCorrectlyMultipleStacks() {
        val player1 = PredictablePlayer(
            listOf(
                Card(Suit.DIAMONDS, Rank.QUEEN),
                Card(Suit.SPADES, Rank.KING),
                Card(Suit.CLUBS, Rank.KING),
                Card(Suit.SPADES, Rank.FOUR)
            ),
            "Player1"
        )
        val player2 = PredictablePlayer(
            listOf(
                Card(Suit.HEARTS, Rank.KING),
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.CLUBS, Rank.TWO)
            ),
            "Player2"
        )

        play.run(player1, player2)

        assertThat(player1.getScore()).isEqualTo(3)
        assertThat(player2.getScore()).isEqualTo(2)

    }

    @Test
    fun runsPlayCorrectlyFirstStackNotFull() {
        val player1 = PredictablePlayer(
            listOf(
                Card(Suit.DIAMONDS, Rank.QUEEN),
                Card(Suit.SPADES, Rank.KING),
                Card(Suit.CLUBS, Rank.KING),
                Card(Suit.SPADES, Rank.FOUR)
            ),
            "Player1"
        )
        val player2 = PredictablePlayer(
            listOf(
                Card(Suit.HEARTS, Rank.KING),
                Card(Suit.DIAMONDS, Rank.TWO),
                Card(Suit.CLUBS, Rank.TWO),
                Card(Suit.CLUBS, Rank.FOUR)
            ),
            "Player2"
        )

        play.run(player1, player2)

        assertThat(player1.getScore()).isEqualTo(3)
        assertThat(player2.getScore()).isEqualTo(3)
    }

}
