package com.example

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import spray.json._
import DefaultJsonProtocol._ // !!! IMPORTANT, else `convertTo` and `toJson` won't work correctly
import spray.routing.directives._
import spray.httpx.SprayJsonSupport._

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
trait PokerPlayerTrait extends HttpService {

  def shutdown()

  val myRoute =
    path("shutdown") {
      complete {
        shutdown()
        "ookay, going down"
      }
    } ~ path("") {
      formField('action) {
        case "check" => complete("We're here!")
        case "version" => complete("0.1.0")
        case "bet_request" => bet_request
        case "showdown" => showdown
        case _ => complete("huh?")
      }
    }
    
  import CardDeserializer._

  val bet_request = {
    formFields('game_state) { game_state: String =>
      println(game_state)
      complete("0")
    } ~ complete("0")
  }

  val showdown = {
    formFields('game_state) { game_state =>

      complete("bang")
    }
  }
}