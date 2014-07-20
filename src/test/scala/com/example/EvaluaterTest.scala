package leanPoker.scalaPlayer.player

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import leanPoker.scalaPlayer.domains.CardEvaluater
import leanPoker.scalaPlayer.domains.Server._

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
    CardEvaluater.highCardEvaluater(cards) should equal(List(1, 14, 4, 2))
  }
  
  "PairEvaluater" should "find pair" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"))
    CardEvaluater.pairEvaluater(cards) should equal(List(2, 4, 14))
  }
  
  "PairEvaluater" should "return 0 without pair" in {
    val cards = List(new Card("4", "spades"), new Card("2", "spades"), new Card("A", "spades"))
    CardEvaluater.pairEvaluater(cards) should equal(List(0))
  }
  
  "TwoPairEvaluater" should "find both pairs" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"), new Card("A", "spades"))
    CardEvaluater.twoPairEvaluater(cards) should equal(List(3, 14, 4))
  }
  
  "TwoPairEvaluater" should "return 0 when pair" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"))
    CardEvaluater.twoPairEvaluater(cards) should equal(List(0))
  }
  
  "TrippleEvaluater" should "find tripple" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"))
    CardEvaluater.trippleEvaluater(cards) should equal(List(4, 4, 14))
  }
  
  "TrippleEvaluater" should "return 0 when pair" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"))
    CardEvaluater.trippleEvaluater(cards) should equal(List(0))
  }

}