package engine.ui

import engine.rule.ScoreSummary

class Terminal : UserInterface {

    override fun clearDisplay() {
        // println("\u001b[H\u001b[2J")
        println("CLEARING...")
    }

    override fun displayMessage(message: String) {
        println(message)
    }

    override fun displayStack(stack: List<String>, count: Int) {
        clearDisplay()
        println("Current stack: ${stack.joinToString("\t")}")
        println("(Total: $count)")
        readln()
    }

    override fun displayHand(cardsText: List<String>) {
        val separator = "\t"
        println(cardsText.joinToString(separator))
        println(List(cardsText.size) { index -> index + 1 }.joinToString(separator))
    }

    override fun displayPlayPoints(selectedCard: String, playerName: String, scoreSummary: ScoreSummary, totalScore: Int) {
        println("$playerName played $selectedCard for ${scoreSummary.score()} points! (Total: $totalScore)")
        scoreSummary.ruleSummaries().forEach {
            if (it.scoringCombinations().isNotEmpty()) {
                val ruleType = it.ruleType()
                it.scoringCombinations().forEach {
                        c -> println("$ruleType of $c")
                }
            }
        }
        readln()
    }

    override fun displayHandPoints(playerName: String, scoreSummary: ScoreSummary, totalScore: Int, isCrib: Boolean) {
        val handName = if (isCrib) {
            "crib"
        } else {
            "hand"
        }
        println("$playerName's $handName scored ${scoreSummary.score()} points! (Total: $totalScore)")
        scoreSummary.ruleSummaries().forEach {
            if (it.scoringCombinations().isNotEmpty()) {
                val ruleType = it.ruleType()
                it.scoringCombinations().forEach {
                    c -> println("$ruleType of $c")
                }
            }
        }
        readln()
    }

    private fun getValidSelections(cardsText: List<String>): List<String> {
        return (1..cardsText.size).map { i -> i.toString() }
    }

    override fun promptPlayerToDiscard(cardsText: List<String>): Set<Int> {
        clearDisplay()
        val validSelections = getValidSelections(cardsText)
        while (true) {
            println("Choose two cards to discard from your hand (e.g. '3 4'):")
            displayHand(cardsText)
            val selections = readln().trim().split(" ").map { s -> s.trim() }
            if (selections.size != 2) {
                println("You must choose exactly two cards...")
                readln()
                clearDisplay()
                continue
            }
            val areValid = selections.all { s -> validSelections.contains(s) }
            if (areValid) {
                return selections.map { s -> s.toInt() }.toSet()
            }
            println("$selections are not not valid choices")
            readln()
            clearDisplay()
        }

    }

    override fun promptPlayerToPlayCard(cardsText: List<String>): Int {
        val validSelections = getValidSelections(cardsText)
        while (true) {
            println("Choose a card to play (e.g. '1'):")
            displayHand(cardsText)
            val selection = readln().trim()
            val isValid = validSelections.contains(selection)
            if (isValid) {
                return selection.toInt()
            }
            println("$selection is not a valid choice")
            readln()
            clearDisplay()
        }
    }

}
