package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class RunsTest {

    private val card = Card(Suit.DIAMONDS, Rank.EIGHT)
    private fun ruleInput(stack: Stack): RuleInput { return RuleInput(stack, card) }

    @Test
    fun scoresRun() {
        val expectedCombination = listOf(
            listOf(Card(Suit.HEARTS, Rank.SIX), Card(Suit.CLUBS, Rank.SEVEN), Card(Suit.DIAMONDS, Rank.EIGHT))
        )
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.SIX),
                Card(Suit.CLUBS, Rank.SEVEN)
            )
        )
        val summary = runs(ruleInput(stack))
        assertThat(summary.scoringCombinations()).isEqualTo(expectedCombination)
        assertThat(summary.points()).isEqualTo(3)
    }

    @Test
    fun scoresRunOutOfOrder() {
        val expectedCombination = listOf(
            listOf(Card(Suit.CLUBS, Rank.SIX), Card(Suit.HEARTS, Rank.SEVEN), Card(Suit.DIAMONDS, Rank.EIGHT))
        )
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.SEVEN),
                Card(Suit.CLUBS, Rank.SIX)
            )
        )
        val summary = runs(ruleInput(stack))
        assertThat(summary.scoringCombinations()).isEqualTo(expectedCombination)
        assertThat(summary.points()).isEqualTo(3)
    }

    @Test
    fun scoresLongRun() {
        val expectedCombination = listOf(
            listOf(
                Card(Suit.DIAMONDS, Rank.FOUR),
                Card(Suit.CLUBS, Rank.FIVE),
                Card(Suit.HEARTS, Rank.SIX),
                Card(Suit.CLUBS, Rank.SEVEN),
                Card(Suit.DIAMONDS, Rank.EIGHT)
            )
        )
        val stack = Stack(
            mutableListOf(
                Card(Suit.CLUBS, Rank.SEVEN),
                Card(Suit.HEARTS, Rank.SIX),
                Card(Suit.DIAMONDS, Rank.FOUR),
                Card(Suit.CLUBS, Rank.FIVE)
            )
        )
        val summary = runs(ruleInput(stack))
        assertThat(summary.scoringCombinations()).isEqualTo(expectedCombination)
        assertThat(summary.points()).isEqualTo(5)
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
        val summary = runs(ruleInput(stack))
        assertThat(summary.scoringCombinations()).isEmpty()
        assertThat(summary.points()).isZero
    }

}
