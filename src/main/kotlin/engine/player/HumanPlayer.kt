package engine.player

import engine.card.Card
import engine.ui.UserInterface

class HumanPlayer(playerName: String, private val ui: UserInterface) : Player(playerName) {

    override fun discard(): Set<Card> {
        val cards = hand.getSortedCards()
        val chosenDiscardIndices = ui.promptPlayerToDiscard(cards.map { c -> c.toString() })
        val chosenDiscards = chosenDiscardIndices.map { i -> cards[i - 1] }.toSet()
        hand.removeCards(chosenDiscards)
        return chosenDiscards
    }

    override fun playCard(cardsPlayed: Set<Card>, stackCount: Int, maxCount: Int): Card {
        val cards = playableCards(cardsPlayed, stackCount, maxCount)
        val chosenCardIndex = ui.promptPlayerToPlayCard(cards.map { c -> c.toString() })
        return cards[chosenCardIndex - 1]
    }

}
