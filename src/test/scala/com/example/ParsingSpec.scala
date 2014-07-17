package com.example

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import spray.json.JsString
import spray.json._
import CardDeserializer._

class ParsingSpec extends FlatSpec with Matchers {
  "GameState serialization" should
    "have an empty game state" in {
      JsString("""{
		  "players":[
		    {
		      "name":"Player 1",
		      "stack":1000,
		      "status":"active",
		      "bet":0,
		      "hole_cards":[],
		      "version":"Version name 1",
		      "id":0
		    },
		    {
		      "name":"Player 2",
		      "stack":1000,
		      "status":"active",
		      "bet":0,	
		      "hole_cards":[],
		      "version":"Version name 2",
		      "id":1
		    }
		  ],
		  "small_blind":10,
		  "orbits":0,
		  "dealer":0,
		  "community_cards":[],
		  "current_buy_in":0,
		  "pot":0
		}
      """).convertTo[GameState] should be(GameState(List(), 0, 0, 0, List(), 0, 0))
    }

}