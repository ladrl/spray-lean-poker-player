package com.example

class Chooser {

  private val evaluaters = List(new TrippleEvaluater(), new TwoPairEvaluater(), new PairEvaluater(), new HighCardEvaluater())

  def choose(cards: List[Card], availableMoney: Int): Int = {
    val sortedResults = evaluaters.map(evaluater => evaluater.evaluate(cards)).sortWith(_.head > _.head)

    if (sortedResults.head.head > 1) {
      return math.round((math.pow(2.0, sortedResults.head.head - 1)) / 8.0 * availableMoney).toInt
    } else {
      return 0
    }
  }
}