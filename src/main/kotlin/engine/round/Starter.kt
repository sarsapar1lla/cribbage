package engine.round

import engine.card.Card
import engine.player.Player
import engine.rule.starter.score
import engine.ui.UserInterface

class Starter(private val ui: UserInterface) {

    fun run(starterCard: Card, dealer: Player) {
        val summary = score(starterCard)
        val points = summary.score()
        dealer.addPoints(points)
        if (points > 0) {
            ui.displayMessage("${dealer.playerName()} scored $points points for drawing a Jack!")
        }
    }

}
