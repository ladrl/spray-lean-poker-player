package leanPoker.scalaPlayer

import domains.Server._

class Chooser {
  private val evaluaters =  {
    import domains.CardEvaluater._
    highCardEvaluater :: pairEvaluater :: trippleEvaluater :: twoPairEvaluater :: Nil
  }

  def choose(cards: List[Card], availableMoney: Int): Int = {
    val sortedResults = evaluaters.map(_(cards)).sortWith(_.head > _.head)

    if (sortedResults.head.head > 1) {
      return math.round((math.pow(2.0, sortedResults.head.head - 1)) / 8.0 * availableMoney).toInt
    } else {
      return 0
    }
  }
}