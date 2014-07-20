package leanPoker.scalaPlayer

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import spray.json.JsString
import spray.json._
import domains.Server.CardDeserializer._
import domains.Server._

class ParsingSpec extends FlatSpec with Matchers {
  "GameState serialization" should
    "have an empty game state" in {
      """{
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
      """.parseJson.convertTo[GameState] should ===(
          GameState(
              Player("Player 1", 1000, "active", 0, Nil, "Version name 1", 0) ::
              Player("Player 2", 1000, "active", 0, Nil, "Version name 2", 1) ::
              Nil, 10 , 0, 0, List(), 0, 0))
    }

}