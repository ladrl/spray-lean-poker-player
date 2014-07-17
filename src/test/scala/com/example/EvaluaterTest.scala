package com.example

import org.specs2.mutable.Specification
import org.specs2.matcher.Matchers
import org.scalatest.FlatSpec

class EvaluaterTest extends FlatSpec {

  "CardEvaluater" should "Jack equals 11" in {
    assert(CardEvaluater.getValue("J")==11)
  }
  
  it should "Queen equals 12" in {
    assert(CardEvaluater.getValue("Q")==12)
  }
  
  it should "King equals 13" in {
    assert(CardEvaluater.getValue("K")==13)
  }
  
  it should "Ace equals 14" in {
    assert(CardEvaluater.getValue("A")==14)
  }
}