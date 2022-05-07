package engine.player

import engine.card.Card

class PredictablePlayer(val cards: List<Card> = emptyList(), playerName: String = "Predictable") : Player(playerName) {

    init {
        giveCards(cards.toSet())
    }

    override fun discard(): Set<Card> {
        return cards.take(2).toSet()
    }

    override fun playCard(cardsPlayed: Set<Card>, stackCount: Int, maxCount: Int): Card {
        return cards.first { c -> c !in cardsPlayed && c.isPlayable(stackCount, maxCount) }
    }

}
