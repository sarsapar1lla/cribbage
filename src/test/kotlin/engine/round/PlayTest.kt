package engine.round

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.player.PredictablePlayer
import engine.rule.play.RulesEngine
import engine.ui.MockUI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

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
        assertEquals(2, player.getScore())
        assertTrue(cards.first() in stack.getCards())
        assertTrue(cards.first() in play.getCardsPlayed())
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

        assertEquals(4, player2.getScore())
        assertEquals(11, player1.getScore())

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

        assertEquals(3, player1.getScore())
        assertEquals(2, player2.getScore())
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

        assertEquals(3, player1.getScore())
        assertEquals(3, player2.getScore())
    }

}
