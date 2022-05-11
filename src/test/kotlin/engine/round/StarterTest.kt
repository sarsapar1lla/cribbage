package engine.round

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import engine.player.PredictablePlayer
import engine.rule.starter.RulesEngine
import engine.ui.MockUI

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class StarterTest {

    private val rulesEngine = RulesEngine()
    private val ui = MockUI()

    private val starter = Starter(rulesEngine, ui)

    @Test
    fun scoresZeroWhenNotJack() {
        val dealer = PredictablePlayer()
        val starterCard = Card(Suit.DIAMONDS, Rank.THREE)

        starter.run(starterCard, dealer)

        assertThat(dealer.score()).isZero
    }

    @Test
    fun scoresPointsAndDisplaysMessageWhenJack() {
        val dealer = PredictablePlayer()
        val starterCard = Card(Suit.DIAMONDS, Rank.JACK)

        starter.run(starterCard, dealer)

        assertThat(dealer.score()).isEqualTo(2)
        assertThat(ui.messages).contains("Predictable scored 2 points for drawing a Jack!")
    }

}
