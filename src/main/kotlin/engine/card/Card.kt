package engine.card

data class Card(private val suit: Suit, private val rank: Rank) {

    fun getSuit(): Suit {
        return suit;
    }

    fun getRank(): Rank {
        return rank;
    }

    fun getCardValue(): Int {
        return rank.cardValue
    }

    override fun toString(): String {
        return "${rank.cardValue}${suit.displayName}"
    }

}
