package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

class CountReachedTest {

    private val fifteenStack = Stack(
        mutableListOf(
            Card(Suit.DIAMONDS, Rank.THREE),
            Card(Suit.CLUBS, Rank.FOUR)
        )
    )
    private fun fifteenRuleInput(card: Card): RuleInput { return RuleInput(fifteenStack, card) }

    private val thirtyOneStack = Stack(
        mutableListOf(
            Card(Suit.DIAMONDS, Rank.TEN),
            Card(Suit.CLUBS, Rank.QUEEN),
            Card(Suit.CLUBS, Rank.EIGHT)
        )
    )

    private fun thirtyOneRuleInput(card: Card): RuleInput { return RuleInput(thirtyOneStack, card) }

    @Test
    fun scoresFifteen() {
        val expectedCombination = listOf(
            listOf(Card(Suit.DIAMONDS, Rank.THREE), Card(Suit.CLUBS, Rank.FOUR), Card(Suit.HEARTS, Rank.EIGHT))
        )
        val card = Card(Suit.HEARTS, Rank.EIGHT)
        val summary = CountReached(DesiredCount.FIFTEEN).apply(fifteenRuleInput(card))
        assertThat(summary.getScoringCombinations()).isEqualTo(expectedCombination)
        assertThat(summary.getPoints()).isEqualTo(2)
    }

    @Test
    fun scoresNotFifteen() {
        val card = Card(Suit.HEARTS, Rank.NINE)
        val summary = CountReached(DesiredCount.FIFTEEN).apply(fifteenRuleInput(card))
        assertThat(summary.getScoringCombinations()).isEmpty()
        assertThat(summary.getPoints()).isZero
    }

    @Test
    fun scoresThirtyOne() {
        val expectedCombination = listOf(
            listOf(
                Card(Suit.HEARTS, Rank.THREE),
                Card(Suit.CLUBS, Rank.EIGHT),
                Card(Suit.DIAMONDS, Rank.TEN),
                Card(Suit.CLUBS, Rank.QUEEN)
            )
        )
        val card = Card(Suit.HEARTS, Rank.THREE)
        val summary = CountReached(DesiredCount.THIRTY_ONE).apply(thirtyOneRuleInput(card))
        assertThat(summary.getScoringCombinations()).isEqualTo(expectedCombination)
        assertThat(summary.getPoints()).isEqualTo(2)
    }

    @Test
    fun scoresNotThirtyOne() {
        val card = Card(Suit.HEARTS, Rank.ACE)
        val summary = CountReached(DesiredCount.THIRTY_ONE).apply(thirtyOneRuleInput(card))
        assertThat(summary.getScoringCombinations()).isEmpty()
        assertThat(summary.getPoints()).isZero
    }

}
