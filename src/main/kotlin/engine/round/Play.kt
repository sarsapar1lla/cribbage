package engine.round

import engine.card.Card
import engine.player.Player
import engine.rule.play.RuleInput
import engine.rule.play.RulesEngine
import engine.ui.UserInterface

class Play(private val rulesEngine: RulesEngine, private val ui: UserInterface) {

    private val cardsPlayed = mutableSetOf<Card>()
    private val lastCardPoints = 1

    internal fun getCardsPlayed(): Set<Card> { return cardsPlayed }

    private fun noPlayerCanGo(players: List<Player>, stack: Stack): Boolean {
        return players.none { p -> p.canGo(cardsPlayed, stack.count(), stack.getMaxCount()) }
    }

    private fun allCardsPlayed(playing: Player, waiting: Player): Boolean {
        return cardsPlayed == playing.getHand().getCards() + waiting.getHand().getCards()
    }

    internal fun playCard(player: Player, stack: Stack) {
        ui.displayStack(stack.getCardStrings(), stack.count())
        val selectedCard = player.playCard(cardsPlayed, stack.count(), stack.getMaxCount())
        val ruleInput = RuleInput(stack, selectedCard)
        val summary = rulesEngine.score(ruleInput)

        player.addPoints(summary.getScore())
        ui.displayPlayPoints(selectedCard.toString(), player.getPlayerName(), summary, player.getScore())

        stack.addCard(selectedCard)
        cardsPlayed.add(selectedCard)
    }

    private fun startNewStack(playing: Player, waiting: Player, playedLastCard: Player) {
        ui.displayMessage("Starting a new stack...")
        if (playedLastCard == playing) {
            run(waiting, playing, playedLastCard)
        } else {
            run(playing, waiting, playedLastCard)
        }
    }

    fun run(playing: Player, waiting: Player, playedLastCard: Player? = null, stack: Stack = Stack()) {

        if (noPlayerCanGo(listOf(playing, waiting), stack)) {
            if (playedLastCard != null && !stack.isFull()) {
                playedLastCard.addPoints(lastCardPoints)
                ui.displayMessage("${playedLastCard.getPlayerName()} scored $lastCardPoints " +
                        "for playing the last card! (Total: ${playedLastCard.getScore()})"
                )
            }
            if (allCardsPlayed(playing, waiting)) {
                ui.displayMessage("All cards played! The play is over")
                return
            }
            if (stack.isFull()) {
                ui.displayMessage("Stack is full!")
            } else {
                ui.displayMessage("Neither player can go!")
            }
            startNewStack(playing, waiting, playedLastCard!!)
            return
        }
        if (playing.canGo(cardsPlayed, stack.count(), stack.getMaxCount())) {
            playCard(playing, stack)
            run(waiting, playing, playing, stack)
            return
        } else {
            ui.displayMessage("${playing.getPlayerName()} cannot go!")
            playCard(waiting, stack)
            run(playing, waiting, waiting, stack)
            return
        }
    }

}
