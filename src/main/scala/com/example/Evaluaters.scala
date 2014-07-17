package com.example

trait CardEvaluater {

  def evaluate(cards: List[Card]): List[Int]
}

class TrippleEvaluater extends CardEvaluater {

  override def evaluate(cards: List[Card]): List[Int] = {
    val cardOccurrences = cards.map(card => CardEvaluater.getValue(card.rank)).foldLeft(Map[Int, Int]() withDefaultValue 0) {
      (m, x) => m + (x -> (1 + m(x)))
    }
    val pairs = cardOccurrences.filter(_._2 == 3).keys.toList.sorted(Ordering[Int].reverse)
    if (pairs.length == 1) {
      (4 :: pairs) ++ cardOccurrences.filter(_._2 == 1).keys.toList.sorted(Ordering[Int].reverse)
    } else {
      List(0)
    }
  }
}

class TwoPairEvaluater extends CardEvaluater {

  override def evaluate(cards: List[Card]): List[Int] = {
    val cardOccurrences = cards.map(card => CardEvaluater.getValue(card.rank)).foldLeft(Map[Int, Int]() withDefaultValue 0) {
      (m, x) => m + (x -> (1 + m(x)))
    }
    val pairs = cardOccurrences.filter(_._2 == 2).keys.toList.sorted(Ordering[Int].reverse)
    if (pairs.length == 2) {
      (3 :: pairs) ++ cardOccurrences.filter(_._2 == 1).keys.toList.sorted(Ordering[Int].reverse)
    } else {
      List(0)
    }
  }
}

class PairEvaluater extends CardEvaluater {

  override def evaluate(cards: List[Card]): List[Int] = {
    val cardOccurrences = cards.map(card => CardEvaluater.getValue(card.rank)).foldLeft(Map[Int, Int]() withDefaultValue 0) {
      (m, x) => m + (x -> (1 + m(x)))
    }
    val pairs = cardOccurrences.filter(_._2 == 2).keys.toList.sorted(Ordering[Int].reverse)
    if (pairs.length == 1) {
      (2 :: pairs) ++ cardOccurrences.filter(_._2 == 1).keys.toList.sorted(Ordering[Int].reverse)
    } else {
      List(0)
    }
  }
}

class HighCardEvaluater extends CardEvaluater {

  override def evaluate(cards: List[Card]): List[Int] = {
    1 :: cards.map(card => CardEvaluater.getValue(card.rank)).sorted(Ordering[Int].reverse)
  }
}

object CardEvaluater {

  def getValue(rank: String): Int = {
    rank match {
      case "A" => 14
      case "K" => 13
      case "Q" => 12
      case "J" => 11
      case Int(out) => out
      case _ => sys.error("illegal rank " + rank)
    }
  }
}

object Int {
  def unapply(s: String): Option[Int] = try {
    Some(s.toInt)
  } catch {
    case _: java.lang.NumberFormatException => None
  }
}