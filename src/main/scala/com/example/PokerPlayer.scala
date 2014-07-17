package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import sun.org.mozilla.javascript.internal.json.JsonParser

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class PokerPlayerActor extends Actor with PokerPlayerTrait {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)

  def shutdown() = {
    context.system.shutdown()
  }
}


// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  def shutdown()

  val myRoute =
    path("shutdown") {
      complete {
    	  shutdown()
        "ookay, going down"
      }
    } ~ path("") {
      get {
        parameter('action) {
          case "check" => complete("We're here!")
          case "version" => complete("0.1.0")
          case "bet_request" => bet_request
          case "showdown" => showdown
          case _ => complete("huh?")
        }
      }
    }

  val bet_request = {
    parameter('game_state) { game_state =>
    complete("0")
    }
  }

  val showdown = {
    parameter('game_state) { game_state =>
    complete("bang")
    }
  }
}