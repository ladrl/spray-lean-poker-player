package com.example

import org.specs2.mutable.Specification
import org.specs2.matcher.Matchers

class EvaluaterTest extends FlatSpec with Matchers {

  "CardEvaluater" must {
    "Jack equals 11" in {
      11 should equal (11)
    }
  }
}