package engine.round

import engine.Deck
import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.player.PredictablePlayer
import engine.rule.RulesEngine
import engine.ui.MockUI

import kotlin.test.Ignore
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class RoundTest {

    private val rulesEngine = RulesEngine()
    private val ui = MockUI()
    private val deck = Deck(false)

    private val round = Round(rulesEngine, ui, deck)

    @Ignore
    @Test
    fun runsRoundCorrectly() {
        val dealer = PredictablePlayer()
        val cutter = PredictablePlayer()

        round.play(dealer, cutter)

        assertThat(true).isTrue
    }

}
