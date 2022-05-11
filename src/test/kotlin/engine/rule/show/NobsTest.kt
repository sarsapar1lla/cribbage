package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class NobsTest {

    private val cards = mutableSetOf(
        Card(Suit.SPADES, Rank.EIGHT),
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.JACK)
    )
    private val hand = Hand(cards)

    private fun ruleInput(starterCard: Card): RuleInput {
        return RuleInput(hand, starterCard)
    }

    @Test
    fun countsNobs() {
        val expectedCombinations = listOf(listOf(Card(Suit.DIAMONDS, Rank.JACK), Card(Suit.DIAMONDS, Rank.KING)))
        val summary = Nobs().apply(ruleInput(Card(Suit.DIAMONDS, Rank.KING)))
        assertThat(summary.scoringCombinations()).isEqualTo(expectedCombinations)
        assertThat(summary.points()).isEqualTo(1)
    }

    @Test
    fun countsNotNobs() {
        val summary = Nobs().apply(ruleInput(Card(Suit.CLUBS, Rank.KING)))
        assertThat(summary.scoringCombinations()).isEmpty()
        assertThat(summary.points()).isZero
    }

}
