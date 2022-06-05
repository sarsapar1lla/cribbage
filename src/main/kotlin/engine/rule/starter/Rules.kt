package engine.rule.starter

import engine.card.Card
import engine.card.Rank
import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

typealias Rule = (Card) -> RuleSummary

internal fun heels(starterCard: Card): RuleSummary {
    if (starterCard.rank() == Rank.JACK) {
        return RuleSummary(RuleType.HEELS, 2, listOf(starterCard))
    }
    return emptyRuleSummary(RuleType.HEELS)
}
