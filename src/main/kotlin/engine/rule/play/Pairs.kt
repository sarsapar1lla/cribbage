package engine.rule.play

class Pairs : Rule {

    override val points = 2

    private fun choose(n: Int, k: Int): Int {
        if (k == 0) {
            return 1
        }
        return n * choose(n - 1, k - 1) / k
    }

    override fun apply(ruleInput: RuleInput): Int {
        val pairs = ruleInput.getStack().takeLastWhile { it.getRank() == ruleInput.getCard().getRank() }
        if (pairs.isEmpty()) {
            return 0
        }
        return choose(pairs.size + 1, 2) * points
    }

}
