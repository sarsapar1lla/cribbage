package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class FlushesTest {

    private val cards = mutableSetOf(
        Card(Suit.DIAMONDS, Rank.NINE),
        Card(Suit.DIAMONDS, Rank.TWO),
        Card(Suit.DIAMONDS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.KING)
    )
    private val hand = Hand(cards)

    private fun ruleInput(starterCard: Card, isCrib: Boolean = false): RuleInput {
        return RuleInput(hand, starterCard, isCrib)
    }

    @Test
    fun countsFourCardFlush() {
        val expectedFlush = listOf(
            listOf(
                Card(Suit.DIAMONDS, Rank.TWO),
                Card(Suit.DIAMONDS, Rank.EIGHT),
                Card(Suit.DIAMONDS, Rank.NINE),
                Card(Suit.DIAMONDS, Rank.KING)
            )
        )
        val ruleInput = ruleInput(Card(Suit.CLUBS, Rank.THREE))
        val summary = Flushes().apply(ruleInput)
        assertThat(summary.scoringCombinations()).isEqualTo(expectedFlush)
        assertThat(summary.points()).isEqualTo(4)
    }

    @Test
    fun countsFiveCardFlush() {
        val expectedFlush = listOf(
            listOf(
                Card(Suit.DIAMONDS, Rank.TWO),
                Card(Suit.DIAMONDS, Rank.THREE),
                Card(Suit.DIAMONDS, Rank.EIGHT),
                Card(Suit.DIAMONDS, Rank.NINE),
                Card(Suit.DIAMONDS, Rank.KING)
            )
        )
        val ruleInput = ruleInput(Card(Suit.DIAMONDS, Rank.THREE))
        val summary = Flushes().apply(ruleInput)
        assertThat(summary.scoringCombinations()).isEqualTo(expectedFlush)
        assertThat(summary.points()).isEqualTo(5)
    }

    @Test
    fun countsFiveCardFlushWhenCrib() {
        val expectedFlush = listOf(
            listOf(
                Card(Suit.DIAMONDS, Rank.TWO),
                Card(Suit.DIAMONDS, Rank.THREE),
                Card(Suit.DIAMONDS, Rank.EIGHT),
                Card(Suit.DIAMONDS, Rank.NINE),
                Card(Suit.DIAMONDS, Rank.KING)
            )
        )
        val ruleInput = ruleInput(Card(Suit.DIAMONDS, Rank.THREE))
        val summary = Flushes().apply(ruleInput)
        assertThat(summary.scoringCombinations()).isEqualTo(expectedFlush)
        assertThat(summary.points()).isEqualTo(5)
    }

    @Test
    fun doesNotCountFourCardFlushWhenCrib() {
        val ruleInput = ruleInput(Card(Suit.HEARTS, Rank.THREE), true)
        val summary = Flushes().apply(ruleInput)
        assertThat(summary.scoringCombinations()).isEmpty()
        assertThat(summary.points()).isZero
    }

}
