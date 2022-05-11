package engine.round

import engine.card.Card
import engine.player.Player
import engine.rule.starter.RulesEngine
import engine.ui.UserInterface

class Starter(private val rulesEngine: RulesEngine, private val ui: UserInterface) {

    fun run(starterCard: Card, dealer: Player) {
        val summary = rulesEngine.score(starterCard)
        val points = summary.getScore()
        dealer.addPoints(points)
        if (points > 0) {
            ui.displayMessage("${dealer.getPlayerName()} scored $points points for drawing a Jack!")
        }
    }

}
