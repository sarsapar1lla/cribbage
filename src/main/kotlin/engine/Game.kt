package engine

import engine.player.HumanPlayer
import engine.player.RandomComputerPlayer
import engine.round.Round
import engine.rule.RulesEngine
import engine.ui.Terminal

class Game {

    private val rulesEngine = RulesEngine()
    private val terminal = Terminal()

    private val player = HumanPlayer("Tim", terminal)
    private val computer = RandomComputerPlayer("HAL")

    fun play() {
        val round = Round(rulesEngine, terminal)
        round.play(computer, player)
    }

}
