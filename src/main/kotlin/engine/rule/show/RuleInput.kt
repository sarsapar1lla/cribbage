package engine.rule.show

import engine.Hand
import engine.card.Card

class RuleInput(private val hand: Hand, private val starterCard: Card, private val isCrib: Boolean = false) {

    private val uniqueCombinations: Set<Set<Card>> = hand.findUniqueCombinations(starterCard)

    fun getHand(): Hand { return hand }

    fun getStarterCard(): Card { return starterCard }

    fun isCrib(): Boolean { return isCrib }

    fun getUniqueCombinations(): Set<Set<Card>> { return uniqueCombinations }

}
