package engine.ui

class Terminal : UserInterface {

    override fun clearDisplay() {
        println("\n")
    }

    override fun displayHand(cardsText: List<String>) {
        val separator = "\t"
        clearDisplay()
        println(cardsText.joinToString(separator))
        println(List(cardsText.size) { index -> index + 1 }.joinToString(separator))
    }

    override fun displayPoints(playerName: String, pointsScored: Int, totalScore: Int) {
        println("$playerName scored $pointsScored points! (Total: $totalScore)")
    }

    private fun getValidSelections(cardsText: List<String>): List<String> {
        return (1..cardsText.size).map { i -> i.toString() }
    }

    override fun promptPlayerToDiscard(cardsText: List<String>): Set<Int> {
        val validSelections = getValidSelections(cardsText)
        while (true) {
            println("Choose two cards to discard from your hand (e.g. '3 4'):")
            displayHand(cardsText)
            val selections = readln().trim().split(" ").map { s -> s.trim() }
            val areValid = selections.all { s -> validSelections.contains(s) }
            if (areValid) {
                return selections.map { s -> s.toInt() }.toSet()
            }
            println("$selections are not not valid choices")
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
            clearDisplay()
        }
    }

}
