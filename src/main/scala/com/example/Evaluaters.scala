package com.example


trait CardEvaluater {
  
  def evaluate(cards: List[Card]): List[Int]
}

class PairEvaluater extends CardEvaluater {
  
  override def evaluate(cards: List[Card]):List[Int] = {
    0::cards.map(card => CardEvaluater.getValue(card.rank)).sorted(Ordering[Int].reverse)
  }
}

class HighCardEvaluater extends CardEvaluater {
  
  override def evaluate(cards: List[Card]):List[Int] = {
    1::cards.map(card => CardEvaluater.getValue(card.rank)).sorted(Ordering[Int].reverse)
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
      case _ => sys.error("illegal rank "+rank)
    }
  }
}

object Int {
  def unapply(s : String) : Option[Int] = try {
    Some(s.toInt)
  } catch {
    case _ : java.lang.NumberFormatException => None
  }
}