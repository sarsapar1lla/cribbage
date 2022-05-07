package engine.card

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CardTest {

    private val card = Card(Suit.CLUBS, Rank.EIGHT)

    @Test
    fun checksIfPlayable() {
        val isPlayable = card.isPlayable(0, 31)
        assertTrue(isPlayable)
    }

    @Test
    fun checksIfNotPlayable() {
        val isPlayable = card.isPlayable(30, 31)
        assertFalse(isPlayable)
    }

}
