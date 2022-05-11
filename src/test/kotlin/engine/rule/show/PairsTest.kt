package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Suit
import engine.card.Rank

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class PairsTest {

    private val cards = mutableSetOf(
        Card(Suit.SPADES, Rank.EIGHT),
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.KING)
    )
    private val hand = Hand(cards)
    private val starterCard = Card(Suit.CLUBS, Rank.KING)
    private val ruleInput = RuleInput(hand, starterCard)

    @Test
    fun countsCorrectNumberOfPairs() {
        val expectedPairs = listOf(
            listOf(Card(Suit.CLUBS, Rank.EIGHT), Card(Suit.DIAMONDS, Rank.EIGHT)),
            listOf(Card(Suit.CLUBS, Rank.EIGHT), Card(Suit.SPADES, Rank.EIGHT)),
            listOf(Card(Suit.DIAMONDS, Rank.EIGHT), Card(Suit.SPADES, Rank.EIGHT)),
            listOf(Card(Suit.CLUBS, Rank.KING), Card(Suit.DIAMONDS, Rank.KING))
        )
        val summary = Pairs().apply(ruleInput)
        assertThat(summary.scoringCombinations()).hasSameElementsAs(expectedPairs)
        assertThat(summary.points()).isEqualTo(8)
    }

}
