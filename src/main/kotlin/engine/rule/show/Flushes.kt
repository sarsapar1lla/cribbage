package engine.rule.show

class Flushes : Rule {

    override val points = 1

    private val fourCardFlushPoints = 4
    private val fiveCardFlushPoints = 5

    override fun apply(ruleInput: RuleInput): Int {
        val uniqueSuits = ruleInput.getHand().getCards().map { c -> c.getSuit() }.distinct()
        if (uniqueSuits.size > 1) {
            return 0
        }
        val starterHasSameSuit = uniqueSuits.contains(ruleInput.getStarterCard().getSuit())
        if (starterHasSameSuit && ruleInput.getHasCrib()) {
            return fiveCardFlushPoints  // five-card flush
        }
        return fourCardFlushPoints  // four-card flush
    }
}
