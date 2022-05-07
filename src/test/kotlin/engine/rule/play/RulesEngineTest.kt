package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class RulesEngineTest {

    private val stack = Stack(
        mutableListOf(
            Card(Suit.HEARTS, Rank.ACE),
            Card(Suit.CLUBS, Rank.FOUR),
            Card(Suit.DIAMONDS, Rank.THREE)
        )
    )
    private fun ruleInput(card: Card): RuleInput { return RuleInput(stack, card) }

    @Test
    fun calculatesCorrectScorePair() {
        val card = Card(Suit.CLUBS, Rank.THREE)
        val score = RulesEngine().score(ruleInput(card))
        assertEquals(2, score)
    }

    @Test
    fun calculatesCorrectScoreRun() {
        val card = Card(Suit.CLUBS, Rank.TWO)
        val score = RulesEngine().score(ruleInput(card))
        assertEquals(4, score)
    }

    @Test
    fun calculatesCorrectScoreFifteen() {
        val card = Card(Suit.CLUBS, Rank.SEVEN)
        val score = RulesEngine().score(ruleInput(card))
        assertEquals(2, score)
    }

    @Test
    fun calculatesCorrectScoreThirtyOne() {
        val stack = Stack(
            mutableListOf(
                Card(Suit.HEARTS, Rank.TEN),
                Card(Suit.DIAMONDS, Rank.QUEEN),
                Card(Suit.CLUBS, Rank.SEVEN)
            )
        )
        val card = Card(Suit.CLUBS, Rank.FOUR)
        val ruleInput = RuleInput(stack, card)
        val score = RulesEngine().score(ruleInput)
        assertEquals(2, score)
    }

}
