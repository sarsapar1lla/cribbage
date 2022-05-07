package engine.rule

class RulesEngine {

    private val starterRulesEngine = engine.rule.starter.RulesEngine()
    private val playRulesEngine = engine.rule.play.RulesEngine()
    private val showRulesEngine = engine.rule.show.RulesEngine()

    fun getStarterRulesEngine(): engine.rule.starter.RulesEngine { return starterRulesEngine }

    fun getPlayRulesEngine(): engine.rule.play.RulesEngine { return playRulesEngine }

    fun getShowRulesEngine(): engine.rule.show.RulesEngine { return showRulesEngine }

}
