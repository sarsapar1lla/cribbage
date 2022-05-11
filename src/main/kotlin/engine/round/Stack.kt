package engine.round

import engine.card.Card

class Stack(private val cards: MutableList<Card> = mutableListOf()) {

    private val maxCount = 31

    fun maxCount(): Int { return maxCount }

    fun count(): Int { return cards.sumOf { c -> c.cardValue() } }

    fun isFull(): Boolean { return count() == maxCount }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun cards(): List<Card> { return cards }

    fun cardsAsStrings(): List<String> {
        return cards.map { c -> c.toString() }
    }

}
