package engine

import engine.player.*
import engine.round.Round
import engine.rule.RulesEngine
import engine.ui.Terminal
import kotlin.random.Random

class Game {

    private val rulesEngine = RulesEngine()
    private val terminal = Terminal()

    private val player = HumanPlayer("Tim", terminal)
    private val computer = RandomComputerPlayer("HAL")

    private fun playerDealsFirst(): Boolean {
        return Random.nextFloat() >= 0.5
    }

    private fun swapRoles(players: Players): Players {
        return Players(players.cutter, players.dealer)
    }


    fun play() {
        var players = if (playerDealsFirst()) Players(player, computer) else Players(computer, player)

        while (true) {
            val round = Round(rulesEngine, terminal)
            try {
                round.play(players)
            } catch (playerHasWon: PlayerHasWonException) {
                terminal.displayMessage(playerHasWon.message!!)
                break
            }
            players = swapRoles(players)
        }
        terminal.displayMessage("Thanks for playing!")
    }

}
