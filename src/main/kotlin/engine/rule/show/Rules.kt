package engine.rule.show

import engine.card.Card
import engine.card.Rank
import engine.rule.RuleSummary
import engine.rule.RuleType
import engine.rule.emptyRuleSummary

typealias Rule = (RuleInput) -> RuleSummary

internal fun fifteens(ruleInput: RuleInput): RuleSummary {
    val fifteens = ruleInput.uniqueCombinations().filter { it.sumOf { c -> c.cardValue() } == 15 }.toSet()
    return RuleSummary(RuleType.FIFTEEN, fifteens.size * 2, fifteens)
}

internal fun flushes(ruleInput: RuleInput): RuleSummary {
    val ruleType = RuleType.FLUSH
    val uniqueSuits = ruleInput.hand().getCards().map { c -> c.suit() }.distinct()
    if (uniqueSuits.size > 1) {
        return emptyRuleSummary(ruleType)
    }
    val starterHasSameSuit = uniqueSuits.contains(ruleInput.starterCard().suit())
    if (starterHasSameSuit) {
        return RuleSummary(
            ruleType,
            5,
            setOf(ruleInput.hand().getCards() + ruleInput.starterCard())
        )
    }
    if (ruleInput.isCrib()) {
        return emptyRuleSummary(ruleType)  // crib can only score a five-card flush
    }
    return RuleSummary(ruleType, 4, setOf(ruleInput.hand().getCards()))
}

internal fun pairs(ruleInput: RuleInput): RuleSummary {
    val pairs = mutableSetOf<Set<Card>>()
    val uniquePairs = ruleInput.uniqueCombinations().filter { c -> c.size == 2 }
    for (pair in uniquePairs) {
        val pairRanks = pair.map { c -> c.rank() }
        if (pairRanks.distinct().count() == 1) {
            pairs.add(pair)
        }
    }
    return RuleSummary(RuleType.PAIR, pairs.size * 2, pairs)
}

internal fun runs(ruleInput: RuleInput): RuleSummary {
    val ruleType = RuleType.RUN
    val runs = mutableListOf<Set<Card>>()
    val possibleRuns = ruleInput.uniqueCombinations().filter { combination -> combination.size >= 3 }
    for (possibleRun in possibleRuns.sortedByDescending { r -> r.size }) {
        val groups = possibleRun.sortedBy {
                card -> card.rank()
        }.mapIndexed {
                index, card -> card.rank().ordinal - index
        }.distinct().count()
        if (groups == 1) {
            runs.add(possibleRun)
            if (possibleRun.size == 5) {
                break  // if there is a run of five cards, then there can't be a longer run to look for
            }
        }
    }
    val maxRunLength = runs.maxOfOrNull { it.size } ?: 0

    if (maxRunLength == 0) {
        return emptyRuleSummary(ruleType)
    }
    return RuleSummary(
        ruleType,
        runs.count { it.size == maxRunLength } * maxRunLength,
        runs.filter { it.size == maxRunLength }.toSet()

    )
}

internal fun nobs(ruleInput: RuleInput): RuleSummary {
    val ruleType = RuleType.NOBS
    val starterCardSuit = ruleInput.starterCard().suit()
    val nobsFilter = { c: Card -> c.suit() == starterCardSuit && c.rank() == Rank.JACK}
    val hasNobs = ruleInput.hand().getCards().any { c -> nobsFilter(c) }
    return if (hasNobs) {
        val scoringCombination = setOf(
            ruleInput.starterCard(),
            ruleInput.hand().getCards().first { c -> nobsFilter(c) }
        )
        RuleSummary(ruleType, 1, setOf(scoringCombination))
    } else {
        emptyRuleSummary(ruleType)
    }
}
