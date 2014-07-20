object tests {
	import com.example._
	import spray.json._
	
	import CardDeserializer._
	
	
	val c = Card("a", "b")                    //> c  : com.example.Card = Card(a,b)
	
	c.toJson                                  //> res0: spray.json.JsValue = {"rank":"a","suite":"b"}
	
	val cardList = List(c, c).toJson          //> cardList  : spray.json.JsValue = [{"rank":"a","suite":"b"},{"rank":"a","suit
                                                  //| e":"b"}]
	
	cardList.convertTo[List[Card]]            //> res1: List[com.example.Card] = List(Card(a,b), Card(a,b))
	
	val p = Player("name", 0, "status", 0, Nil, "version", 0)
                                                  //> p  : com.example.Player = Player(name,0,status,0,List(),version,0)
	
	val playerStr = p.toJson.compactPrint     //> playerStr  : String = {"name":"name","stack":0,"status":"status","bet":0,"ho
                                                  //| le_cards":[],"version":"version","id":0}
           
  playerStr.parseJson.convertTo[Player]           //> res2: com.example.Player = Player(name,0,status,0,List(),version,0)

	val player2 = p.copy(hole_cards = c :: Nil).toJson.compactPrint
                                                  //> player2  : String = {"name":"name","stack":0,"status":"status","bet":0,"hole
                                                  //| _cards":[{"rank":"a","suite":"b"}],"version":"version","id":0}
                                                  
	player2.parseJson.convertTo[Player]       //> res3: com.example.Player = Player(name,0,status,0,List(Card(a,b)),version,0)
                                                  //| 

	val gs = GameState(p :: Nil, 0, 0, 0, c :: Nil, 0, 0)
                                                  //> gs  : com.example.GameState = GameState(List(Player(name,0,status,0,List(),v
                                                  //| ersion,0)),0,0,0,List(Card(a,b)),0,0)
	
  val gsStr =	gs.toJson.compactPrint            //> gsStr  : String = {"players":[{"name":"name","stack":0,"status":"status","be
                                                  //| t":0,"hole_cards":[],"version":"version","id":0}],"small_blind":0,"orbits":0
                                                  //| ,"dealer":0,"community_cards":[{"rank":"a","suite":"b"}],"current_buy_in":0,
                                                  //| "pot":0}
  
  gsStr.parseJson.convertTo[GameState]            //> res4: com.example.GameState = GameState(List(Player(name,0,status,0,List(),v
                                                  //| ersion,0)),0,0,0,List(Card(a,b)),0,0)
  
}