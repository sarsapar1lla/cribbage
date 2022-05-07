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
        val heels = Heels().apply(starterCard)
        assertThat(heels).isEqualTo(2)
    }

    @Test
    fun scoresNotHeels() {
        val starterCard = Card(Suit.SPADES, Rank.QUEEN)
        val heels = Heels().apply(starterCard)
        assertThat(heels).isZero
    }

}
