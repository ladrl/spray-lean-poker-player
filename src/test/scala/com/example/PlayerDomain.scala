package leanPoker.scalaPlayer.domains

object Player {

  case class Card(rank: Int, suite: String)

  object State extends Enumeration {
    type State = Value
    val Active, Folded, Out = Value
  }

  case class Player(name: String, stack: Int, status: State.Value, bet: Int, hole_cards: List[Card], version: String, id: Int)

  case class GameState(players: List[Player], small_blind: Int, orbits: Int, dealer: Int, community_cards: List[Card], current_buy_in: Int, pot: Int)

}