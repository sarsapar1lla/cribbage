package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class RunsTest {

    private val cards = mutableSetOf(
        Card(Suit.CLUBS, Rank.SEVEN),
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.CLUBS, Rank.NINE),
        Card(Suit.DIAMONDS, Rank.TEN)
    )
    private val hand = Hand(cards)

    @Test
    fun countsRuns() {
        val expectedRuns = listOf(
            listOf(
                Card(Suit.CLUBS, Rank.SEVEN),
                Card(Suit.CLUBS, Rank.EIGHT),
                Card(Suit.CLUBS, Rank.NINE),
                Card(Suit.DIAMONDS, Rank.TEN)
            )
        )
        val starterCard = Card(Suit.CLUBS, Rank.TWO)
        val ruleInput = RuleInput(hand, starterCard)
        val summary = Runs().apply(ruleInput)
        assertThat(summary.getScoringCombinations()).isEqualTo(expectedRuns)
        assertThat(summary.getPoints()).isEqualTo(4)
    }

    @Test
    fun countsMultipleRuns() {
        val expectedRuns = listOf(
            listOf(
                Card(Suit.CLUBS, Rank.SEVEN),
                Card(Suit.CLUBS, Rank.EIGHT),
                Card(Suit.CLUBS, Rank.NINE),
                Card(Suit.DIAMONDS, Rank.TEN)
            ),
            listOf(
                Card(Suit.CLUBS, Rank.SEVEN),
                Card(Suit.CLUBS, Rank.EIGHT),
                Card(Suit.DIAMONDS, Rank.NINE),
                Card(Suit.DIAMONDS, Rank.TEN)
            )
        )
        val starterCard = Card(Suit.DIAMONDS, Rank.NINE)
        val ruleInput = RuleInput(hand, starterCard)
        val summary = Runs().apply(ruleInput)
        assertThat(summary.getScoringCombinations()).isEqualTo(expectedRuns)
        assertThat(summary.getPoints()).isEqualTo(8)
    }

}
