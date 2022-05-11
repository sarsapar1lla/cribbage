package engine.card

data class Card(private val suit: Suit, private val rank: Rank) {

    fun suit(): Suit { return suit }

    fun rank(): Rank { return rank }

    fun cardValue(): Int {
        return rank.cardValue
    }

    fun isPlayable(stackCount: Int, maxCount: Int): Boolean {
        return stackCount + rank.cardValue <= maxCount
    }

    override fun toString(): String {
        return "${rank.displayName}${suit.displayName}"
    }

}
