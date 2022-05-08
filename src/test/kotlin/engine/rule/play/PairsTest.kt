package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class PairsTest {

    private val card = Card(Suit.DIAMONDS, Rank.THREE)
    private fun ruleInput(stack: Stack): RuleInput { return RuleInput(stack, card) }

    @Test
    fun scoresPairs() {
        val expectedCombination = listOf(
            listOf(Card(Suit.HEARTS, Rank.THREE), Card(Suit.CLUBS, Rank.THREE), Card(Suit.DIAMONDS, Rank.THREE))
        )
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.THREE),
                Card(Suit.CLUBS, Rank.THREE)
            )
        )
        val summary = Pairs().apply(ruleInput(stack))
        assertThat(summary.getScoringCombinations()).isEqualTo(expectedCombination)
        assertThat(summary.getPoints()).isEqualTo(6)
    }

    @Test
    fun scoresNotPairs() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.THREE),
                Card(Suit.CLUBS, Rank.FOUR)
            )
        )
        val summary = Pairs().apply(ruleInput(stack))
        assertThat(summary.getScoringCombinations()).isEmpty()
        assertThat(summary.getPoints()).isZero
    }

}
