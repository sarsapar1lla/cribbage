package engine.rule.starter

import engine.card.Card
import engine.card.Rank
import engine.card.Suit
import kotlin.test.Test
import kotlin.test.assertEquals

class HeelsTest {

    @Test
    fun scoresHeels() {
        val starterCard = Card(Suit.SPADES, Rank.JACK)
        val heels = Heels().apply(starterCard)
        assertEquals(2, heels)
    }

    @Test
    fun scoresNotHeels() {
        val starterCard = Card(Suit.SPADES, Rank.QUEEN)
        val heels = Heels().apply(starterCard)
        assertEquals(0, heels)
    }

}
