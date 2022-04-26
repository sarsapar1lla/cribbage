package engine.rule.show

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class RulesEngineTest {

    private val cards = setOf(
        Card(Suit.CLUBS, Rank.SEVEN),
        Card(Suit.CLUBS, Rank.EIGHT),
        Card(Suit.CLUBS, Rank.NINE),
        Card(Suit.CLUBS, Rank.TEN)
    )
    private val hand = Hand(cards)

    @Test
    fun calculatesCorrectScore() {
        val starterCard = Card(Suit.DIAMONDS, Rank.FIVE)
        val ruleInput = RuleInput(hand, starterCard)
        val score = RulesEngine().score(ruleInput)
        assertEquals(12, score)
    }

}
