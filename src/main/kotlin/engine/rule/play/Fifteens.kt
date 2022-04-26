package engine.rule.play

class Fifteens : Rule {

    override val points = 2

    override fun apply(ruleInput: RuleInput): Int {
        val isFifteen = ruleInput.getRunningTotal() + ruleInput.getCard().getCardValue() == 15
        return if (isFifteen) {
            points
        } else {
            0
        }
    }

}
