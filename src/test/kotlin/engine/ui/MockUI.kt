package engine.ui

import engine.rule.ScoreSummary

internal class MockUI(private val discardChoices: Set<Int> = emptySet(), private val playCardChoice: Int = 1) : UserInterface {

    val messages = mutableListOf<String>()

    override fun clearDisplay() {}

    override fun displayMessage(message: String) { messages.add(message) }

    override fun displayStack(stack: List<String>, count: Int) {}

    override fun displayHand(cardsText: List<String>) {}

    override fun displayPlayPoints(selectedCard: String, playerName: String, scoreSummary: ScoreSummary, totalScore: Int) {}

    override fun displayHandPoints(playerName: String, scoreSummary: ScoreSummary, totalScore: Int, isCrib: Boolean) {}

    override fun promptPlayerToDiscard(cardsText: List<String>): Set<Int> {
        return discardChoices
    }

    override fun promptPlayerToPlayCard(cardsText: List<String>): Int {
        return playCardChoice
    }

}
