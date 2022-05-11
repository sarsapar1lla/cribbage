package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.rule.fixtures.JunkHand

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class FifteensTest {

    private val cards = mutableSetOf(
        Card(Suit.HEARTS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.SEVEN),
        Card(Suit.DIAMONDS, Rank.ACE),
        Card(Suit.CLUBS, Rank.KING)
    )
    private val starterCard = Card(Suit.HEARTS, Rank.FOUR)
    private val hand = Hand(cards)
    private val ruleInput = RuleInput(hand, starterCard)

    @Test
    fun returnsCorrectCount() {
        val expectedFifteens = listOf(
            listOf(Card(Suit.DIAMONDS, Rank.SEVEN), Card(Suit.HEARTS, Rank.EIGHT)),
            listOf(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.HEARTS, Rank.FOUR), Card(Suit.CLUBS, Rank.KING))
        )
        val summary = Fifteens().apply(ruleInput)
        assertThat(summary.scoringCombinations()).isEqualTo(expectedFifteens)
        assertThat(summary.points()).isEqualTo(4)
    }

    @Test
    fun returnsCorrectCountWithJunkHand() {
        val summary = Fifteens().apply(RuleInput(JunkHand().hand(), starterCard))
        assertThat(summary.scoringCombinations()).isEmpty()
        assertThat(summary.points()).isZero
    }

}