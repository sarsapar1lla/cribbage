package engine

import engine.card.Card

class Player(val playerName: String, var hand: Hand) {

    fun scoreHand(starterCard: Card): Int {
        return 1;
    }

}
