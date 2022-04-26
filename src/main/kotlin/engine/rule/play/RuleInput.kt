package engine.rule.play

import engine.card.Card

class RuleInput(private val stack: List<Card>, private val card: Card) {

    private val runningTotal: Int = stack.sumOf { c -> c.getRank().cardValue } + card.getCardValue()

    fun getStack(): List<Card> { return stack }

    fun getCard(): Card { return card }

    fun getRunningTotal(): Int { return runningTotal }

}
