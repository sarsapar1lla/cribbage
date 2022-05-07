package engine.rule.starter

import engine.card.Card
import engine.card.Rank

class Heels : Rule {

    override val points: Int = 2

    override fun apply(starterCard: Card): Int {
        if (starterCard.getRank() == Rank.JACK) {
            return points
        }
        return 0
    }

}
