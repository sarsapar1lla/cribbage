package engine

import engine.player.HumanPlayer
import engine.player.RandomComputerPlayer
import engine.player.PlayerHasWonException
import engine.round.Round
import engine.rule.RulesEngine
import engine.ui.Terminal

class Game {

    private val rulesEngine = RulesEngine()
    private val terminal = Terminal()

    private val player = HumanPlayer("Tim", terminal)
    private val computer = RandomComputerPlayer("HAL")

    fun play() {
        while (true) {
            val round = Round(rulesEngine, terminal)
            try {
                round.play(computer, player)
            } catch (playerHasWon: PlayerHasWonException) {
                terminal.displayMessage(playerHasWon.message!!)
                break
            }
        }
        terminal.displayMessage("Thanks for playing!")
    }

}
