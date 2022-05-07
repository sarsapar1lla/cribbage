package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.rule.fixtures.JunkHand
import engine.rule.show.Fifteens
import engine.rule.show.RuleInput
import kotlin.test.Test
import kotlin.test.assertEquals

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
        val fifteens = Fifteens().apply(ruleInput)
        assertEquals(4, fifteens)
    }

    @Test
    fun returnsCorrectCountWithJunkHand() {
        val fifteens = Fifteens().apply(RuleInput(JunkHand().hand(), starterCard))
        assertEquals(0, fifteens)
    }

}