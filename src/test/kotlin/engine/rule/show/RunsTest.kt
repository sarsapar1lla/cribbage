package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.rule.show.RuleInput
import engine.rule.show.Runs
import kotlin.test.Test
import kotlin.test.assertEquals

class RunsTest {

    private val cards = setOf(
        Card(Suit.CLUBS, Rank.SEVEN),
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.CLUBS, Rank.NINE),
        Card(Suit.DIAMONDS, Rank.TEN)
    )
    private val hand = Hand(cards)

    @Test
    fun countsRuns() {
        val starterCard = Card(Suit.CLUBS, Rank.TWO)
        val ruleInput = RuleInput(hand, starterCard)
        val runs = Runs().apply(ruleInput)
        assertEquals(4, runs)
    }

    @Test
    fun countsMultipleRuns() {
        val starterCard = Card(Suit.DIAMONDS, Rank.NINE)
        val ruleInput = RuleInput(hand, starterCard)
        val runs = Runs().apply(ruleInput)
        assertEquals(8, runs)
    }

}
