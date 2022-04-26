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

    private fun dealOne(): Card {
        val card = getRemainingCards().first()
        dealtCards.add(card)
        return card
    }

    fun getRemainingCards(): List<Card> {
        return cards.filter { c -> c !in dealtCards }
    }

    fun getDealtCards(): List<Card> {
        return dealtCards
    }

    fun deal(): Pair<Hand, Hand> {
        val firstSet = mutableSetOf<Card>()
        val secondSet = mutableSetOf<Card>()
        for (i in 1..6) {
            firstSet.add(dealOne())
            secondSet.add(dealOne())
        }
        return Pair(Hand(firstSet), Hand(secondSet))
    }

}
