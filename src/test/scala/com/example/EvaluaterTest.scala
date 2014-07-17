package com.example

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class EvaluaterTest extends FlatSpec with Matchers {

  "CardEvaluater" should "Jack equals 11" in {
    CardEvaluater.getValue("J") should equal(11)
  }

  it should "Queen equals 12" in {
    CardEvaluater.getValue("Q") should equal(12)
  }

  it should "King equals 13" in {
    CardEvaluater.getValue("K") should equal(13)
  }

  it should "Ace equals 14" in {
    CardEvaluater.getValue("A") should equal(14)
  }

  it should "Number equals number" in {
    CardEvaluater.getValue("8") should equal(8)
  }

  "HighCardEvaluater" should "order cards" in {
    val cards = List(new Card("4", "spades"), new Card("2", "spades"), new Card("A", "spades"))
    new HighCardEvaluater().evaluate(cards) should equal(List(1, 14, 4, 2))
  }

}