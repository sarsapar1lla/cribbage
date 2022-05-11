package engine.player

import engine.Hand
import engine.card.Card
import kotlin.math.min

abstract class Player(private val playerName: String) {

    private var score = 0

    protected val hand: Hand = Hand()

    fun score(): Int { return score }

    fun addPoints(points: Int) {
        val newScore = score + points
        score = min(newScore, 121)
    }

    fun playerName(): String { return playerName }

    @JvmName("getPlayerHand")
    fun hand(): Hand { return hand }

    fun giveCards(cards: Set<Card>) {
        hand.replaceCards(cards)
    }

    fun canGo(cardsPlayed: Set<Card>, stackCount: Int, maxCount: Int): Boolean {
        return playableCards(cardsPlayed, stackCount, maxCount).isNotEmpty()
    }

    protected fun playableCards(cardsPlayed: Set<Card>, stackCount: Int, maxCount: Int): List<Card> {
        return hand.getSortedCards().filter { c -> c !in cardsPlayed && c.isPlayable(stackCount, maxCount)}
    }

    abstract fun discard(): Set<Card>

    abstract fun playCard(cardsPlayed: Set<Card>, stackCount: Int, maxCount: Int): Card

}
