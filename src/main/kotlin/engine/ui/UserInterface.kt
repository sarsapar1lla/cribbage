package engine.ui

interface UserInterface {

    fun clearDisplay()

    fun displayHand(cardsText: List<String>)

    fun displayPoints(playerName: String, pointsScored: Int, totalScore: Int)

    fun promptPlayerToDiscard(cardsText: List<String>): Set<Int>

    fun promptPlayerToPlayCard(cardsText: List<String>): Int

}
