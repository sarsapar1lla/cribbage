package engine.rule.show

import engine.card.Rank

class Nobs : Rule {

    override val points = 1

    override fun apply(ruleInput: RuleInput): Int {
        val starterCardSuit = ruleInput.getStarterCard().getSuit()
        val hasNobs = ruleInput.getHand().getCards().any { c -> c.getSuit() == starterCardSuit && c.getRank() == Rank.JACK }
        return if (hasNobs) {
            points
        } else {
            0
        }
    }
}
