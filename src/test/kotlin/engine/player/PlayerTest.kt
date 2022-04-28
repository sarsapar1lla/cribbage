package engine.player

import engine.ui.MockUI
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerTest {

    private val player = HumanPlayer("Tester", MockUI())  // using HumanPlayer instance to test concrete methods

    @Test
    fun addsPoints() {
        player.addPoints(5)
        assertEquals(5, player.getScore())
    }

    @Test
    fun capsScoreAt121() {
        player.addPoints(122)
        assertEquals(121, player.getScore())
    }

}
