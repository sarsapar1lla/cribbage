package engine.ui

interface UserInterface {

    fun clearDisplay()

    fun displayMessage(message: String)

    fun displayStack(stack: List<String>, count: Int)

    fun displayHand(cardsText: List<String>)

    fun displayPlayPoints(selectedCard: String, playerName: String, pointsScored: Int, totalScore: Int)

    fun displayHandPoints(playerName: String, pointsScored: Int, totalScore: Int, isCrib: Boolean = false)

    fun promptPlayerToDiscard(cardsText: List<String>): Set<Int>

    fun promptPlayerToPlayCard(cardsText: List<String>): Int

}