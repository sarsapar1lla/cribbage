package engine.rule.show

class Fifteens : Rule {

    override val points = 2

    override fun apply(ruleInput: RuleInput): Int {
        return ruleInput.getUniqueCombinations().map { it.sumOf { c -> c.getCardValue() } }.count { it == 15 } * points
    }
}
