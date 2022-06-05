package engine.rule.play

import engine.round.Stack
import engine.card.Card

data class RuleInput(private val stack: Stack, private val card: Card) {

    private val runningTotal: Int = stack.count() + card.cardValue()

    fun stack(): Stack { return stack }

    fun card(): Card { return card }

    fun runningTotal(): Int { return runningTotal }

}
