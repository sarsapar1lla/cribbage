package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.rule.show.Nobs
import engine.rule.show.RuleInput
import kotlin.test.Test
import kotlin.test.assertEquals

class NobsTest {

    private val cards = setOf(
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
        val nobs = Nobs().apply(ruleInput(Card(Suit.DIAMONDS, Rank.KING)))
        assertEquals(1, nobs)
    }

    @Test
    fun countsNotNobs() {
        val nobs = Nobs().apply(ruleInput(Card(Suit.CLUBS, Rank.KING)))
        assertEquals(0, nobs)
    }

}