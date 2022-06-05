package engine.rule.show

import engine.Hand
import engine.card.Card

data class RuleInput(private val hand: Hand, private val starterCard: Card, private val isCrib: Boolean = false) {

    private val uniqueCombinations: Set<Set<Card>> = hand.findUniqueCombinations(starterCard)

    fun hand(): Hand { return hand }

    fun starterCard(): Card { return starterCard }

    fun isCrib(): Boolean { return isCrib }

    fun uniqueCombinations(): Set<Set<Card>> { return uniqueCombinations }

}
