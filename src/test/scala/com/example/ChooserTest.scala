package com.example

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class ChooserTest extends FlatSpec with Matchers {
  
  "Chooser" should "invest all on tripple" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"))
    new Chooser().choose(cards, 1000) should equal(1000)
  }
  
  it should "invest quarter on pair" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("2", "spades"), new Card("A", "spades"))
    new Chooser().choose(cards, 1000) should equal(250)
  }
  
  it should "invest half on two pair" in {
    val cards = List(new Card("4", "spades"), new Card("4", "spades"), new Card("A", "spades"), new Card("A", "spades"))
    new Chooser().choose(cards, 1000) should equal(500)
  }
  
  it should "invest none on high card" in {
    val cards = List(new Card("4", "spades"), new Card("2", "spades"), new Card("3", "spades"), new Card("A", "spades"))
    new Chooser().choose(cards, 1000) should equal(0)
  }
}