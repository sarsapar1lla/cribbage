package engine.rule.play

import engine.round.Stack
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

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
        val summary = RulesEngine().score(ruleInput(card))
        assertThat(summary.score()).isEqualTo(2)
    }

    @Test
    fun calculatesCorrectScoreRun() {
        val card = Card(Suit.CLUBS, Rank.TWO)
        val summary = RulesEngine().score(ruleInput(card))
        assertThat(summary.score()).isEqualTo(4)
    }

    @Test
    fun calculatesCorrectScoreFifteen() {
        val card = Card(Suit.CLUBS, Rank.SEVEN)
        val summary = RulesEngine().score(ruleInput(card))
        assertThat(summary.score()).isEqualTo(2)
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
        val summary = RulesEngine().score(ruleInput)
        assertThat(summary.score()).isEqualTo(2)
    }

}
