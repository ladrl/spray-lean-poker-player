package com.example

case class Card(rank: String, suite: String)

case class Player(name: String, stack: Int, status: String, bet: Int, hole_cards: List[Card], version: String, id: Int)

case class GameState(players: List[Player], small_blind: Int, orbits: Int, dealer: Int, community_cards: List[Card], current_buy_in: Int, pot: Int)