package engine.rule.starter

import engine.card.Card
import engine.card.Rank
import engine.card.Suit

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class HeelsTest {

    @Test
    fun scoresHeels() {
        val starterCard = Card(Suit.SPADES, Rank.JACK)
        val summary = heels(starterCard)
        assertThat(summary.scoringCombinations()).isEqualTo(listOf(listOf(Card(Suit.SPADES, Rank.JACK))))
        assertThat(summary.points()).isEqualTo(2)
    }

    @Test
    fun scoresNotHeels() {
        val starterCard = Card(Suit.SPADES, Rank.QUEEN)
        val summary = heels(starterCard)
        assertThat(summary.scoringCombinations()).isEmpty()
        assertThat(summary.points()).isZero
    }

}
