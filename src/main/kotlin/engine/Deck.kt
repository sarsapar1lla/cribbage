package engine

import engine.card.Card
import engine.card.Rank
import engine.card.Suit

class Deck(shuffled: Boolean = true) {

    private val cards: List<Card>
    private val dealtCards = mutableListOf<Card>()

    init {
        val cards = generateCards()
        if (shuffled) {
            this.cards = cards.shuffled()
        } else {
            this.cards = cards
        }
    }

    private fun generateCards(): List<Card> {
        val cards = mutableListOf<Card>()
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
                cards.add(Card(suit, rank))
            }
        }
        return cards
    }

    fun dealOne(): Card {
        val card = remainingCards().first()
        dealtCards.add(card)
        return card
    }

    fun remainingCards(): List<Card> {
        return cards.filterNot { c -> c in dealtCards }
    }

    fun dealtCards(): List<Card> {
        return dealtCards
    }

    fun deal(): Pair<Set<Card>, Set<Card>> {
        val firstSet = mutableSetOf<Card>()
        val secondSet = mutableSetOf<Card>()
        for (i in 1..6) {
            firstSet.add(dealOne())
            secondSet.add(dealOne())
        }
        return Pair(firstSet, secondSet)
    }

}
