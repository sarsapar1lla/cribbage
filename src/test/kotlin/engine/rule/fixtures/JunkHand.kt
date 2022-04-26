package engine.rule.fixtures

import engine.Hand
import engine.card.Card
import engine.card.Rank
import engine.card.Suit

class JunkHand {

    private val cards = setOf(
        Card(Suit.HEARTS, Rank.EIGHT),
        Card(Suit.DIAMONDS, Rank.TWO),
        Card(Suit.DIAMONDS, Rank.FOUR),
        Card(Suit.CLUBS, Rank.KING)
    )
    private val hand = Hand(cards)

    fun hand(): Hand {
        return hand
    }

}
