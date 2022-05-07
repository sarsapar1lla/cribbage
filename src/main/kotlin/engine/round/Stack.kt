package engine.round

import engine.card.Card

class Stack(private val cards: MutableList<Card> = mutableListOf()) {

    private val maxCount = 31

    fun getMaxCount(): Int { return maxCount }

    fun count(): Int { return cards.sumOf { c -> c.getCardValue() } }

    fun isFull(): Boolean { return count() == maxCount }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> { return cards }

    fun getCardStrings(): List<String> {
        return cards.map { c -> c.toString() }
    }

}
