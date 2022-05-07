package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class RunsTest {

    private val card = Card(Suit.DIAMONDS, Rank.EIGHT)
    private fun ruleInput(stack: Stack): RuleInput { return RuleInput(stack, card) }

    @Test
    fun scoresRun() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.SIX),
                Card(Suit.CLUBS, Rank.SEVEN)
            )
        )
        val runs = Runs().apply(ruleInput(stack))
        assertEquals(3, runs)
    }

    @Test
    fun scoresRunOutOfOrder() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.SEVEN),
                Card(Suit.CLUBS, Rank.SIX)
            )
        )
        val runs = Runs().apply(ruleInput(stack))
        assertEquals(3, runs)
    }

    @Test
    fun scoresLongRun() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.SEVEN),
                Card(Suit.CLUBS, Rank.SIX),
                Card(Suit.DIAMONDS, Rank.FOUR),
                Card(Suit.CLUBS, Rank.FIVE)
            )
        )
        val runs = Runs().apply(ruleInput(stack))
        assertEquals(5, runs)
    }

    @Test
    fun scoresNotRun() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.SIX),
                Card(Suit.CLUBS, Rank.SEVEN),
                Card(Suit.DIAMONDS, Rank.TWO)
            )
        )
        val runs = Runs().apply(ruleInput(stack))
        assertEquals(0, runs)
    }

}
