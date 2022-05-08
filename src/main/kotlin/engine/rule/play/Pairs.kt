package engine.rule.play

import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

class Pairs : Rule {

    override val ruleType = RuleType.PAIR

    override val points = 2

    private fun choose(n: Int, k: Int): Int {
        if (k == 0) {
            return 1
        }
        return n * choose(n - 1, k - 1) / k
    }

    override fun apply(ruleInput: RuleInput): RuleSummary {
        val pairs = ruleInput.getStack().getCards().takeLastWhile { it.getRank() == ruleInput.getCard().getRank() }
        if (pairs.isEmpty()) {
            return emptyRuleSummary(ruleType)
        }
        return RuleSummary(
            ruleType,
            choose(pairs.size + 1, 2) * points,
            pairs + ruleInput.getCard()
        )
    }

}
