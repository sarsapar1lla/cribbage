package engine.ui

class MockUI(private val discardChoices: Set<Int> = emptySet(), private val playCardChoice: Int = 1) : UserInterface {

    override fun clearDisplay() {}

    override fun displayHand(cardsText: List<String>) {}

    override fun displayPoints(playerName: String, pointsScored: Int, totalScore: Int) {}

    override fun promptPlayerToDiscard(cardsText: List<String>): Set<Int> {
        return discardChoices
    }

    override fun promptPlayerToPlayCard(cardsText: List<String>): Int {
        return playCardChoice
    }

}
