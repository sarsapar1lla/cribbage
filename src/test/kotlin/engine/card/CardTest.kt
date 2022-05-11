package engine.card

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat

internal class CardTest {

    private val card = Card(Suit.CLUBS, Rank.EIGHT)

    @Test
    fun checksIfPlayable() {
        val isPlayable = card.isPlayable(0, 31)
        assertThat(isPlayable).isTrue
    }

    @Test
    fun checksIfNotPlayable() {
        val isPlayable = card.isPlayable(30, 31)
        assertThat(isPlayable).isFalse
    }

}
