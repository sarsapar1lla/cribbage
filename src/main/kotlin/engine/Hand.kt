package engine

import engine.card.Card

data class Hand(private val cards: MutableSet<Card> = mutableSetOf()) {

    fun removeCards(cards: Set<Card>) {
        this.cards.removeAll(cards)
    }

    fun getCards(): Set<Card> {
        return cards
    }

    fun getSortedCards(): List<Card> {
        return cards.sortedWith(compareBy({it.suit()}, {it.rank()}))
    }

    fun replaceCards(cards: Set<Card>) {
        this.cards.clear()
        this.cards.addAll(cards)
    }

    private fun findUniqueCombinationsOfLengthN(cards: Set<Card>, n: Int): MutableSet<MutableSet<Card>> {

        if (n == 0) {  // unique combinations of length zero is an empty set of empty sets
            return mutableSetOf(mutableSetOf())
        }

        if (cards.isEmpty()) {  // unique combinations of an empty set is an empty set
            return mutableSetOf()
        }

        val uniqueCombinations = mutableSetOf<MutableSet<Card>>()

        /*
        Unique combinations of length N for each card X are:
        - All unique combinations of length N - 1 cards plus X
        - All unique combinations of length N not including X
         */
        for (card in cards) {
            val otherCards = cards.minus(card)
            val smallerSubsets = findUniqueCombinationsOfLengthN(otherCards, n - 1)
            smallerSubsets.forEach { s -> uniqueCombinations.add((s + card).toMutableSet()) }
        }
        return uniqueCombinations
    }

    fun findUniqueCombinations(starterCard: Card): Set<Set<Card>> {
        val cards = this.cards + starterCard
        val uniqueCombinations = mutableSetOf<MutableSet<Card>>()
        for (n in 1..5) {
            uniqueCombinations.addAll(findUniqueCombinationsOfLengthN(cards, n))
        }
        return uniqueCombinations
    }
}
