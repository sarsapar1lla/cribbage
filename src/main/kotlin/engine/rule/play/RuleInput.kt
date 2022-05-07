package engine.rule.play

import engine.round.Stack
import engine.card.Card

class RuleInput(private val stack: Stack, private val card: Card) {

    private val runningTotal: Int = stack.count() + card.getCardValue()

    fun getStack(): Stack { return stack }

    fun getCard(): Card { return card }

    fun getRunningTotal(): Int { return runningTotal }

}
