package engine.player

import engine.card.Card

class RandomComputerPlayer(playerName: String) : Player(playerName) {

    override fun discard(): Set<Card> {
        val chosenDiscards = hand.getCards().shuffled().take(2).toSet()  // chooses a random pair of cards
        hand.removeCards(chosenDiscards)
        return chosenDiscards
    }

    override fun playCard(cardsPlayed: Set<Card>, stackCount: Int, maxCount: Int): Card {
        val cards = playableCards(cardsPlayed, stackCount, maxCount)
        return cards.random()  // chooses a random playable card
    }

}
