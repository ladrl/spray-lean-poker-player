package leanPoker.scalaPlayer

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import spray.json._
import DefaultJsonProtocol._
import spray.routing.directives._
import spray.httpx.SprayJsonSupport._
import scala.util.Random
import akka.actor.ActorRef
import akka.util.Timeout
import akka.actor.Props

import domains.Server._

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor

object LeanPokerCommunicationActor {
  def props(playerRef: ActorRef) = Props(new LeanPokerCommunicationActor(playerRef))
}
class LeanPokerCommunicationActor(val playerRef: ActorRef) extends Actor with PokerPlayerTrait {

  println("Version: " + getVersion)

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

  def getVersion() = {
    "0.3.0"
  }
}

// this trait defines our service behavior independently from the service actor
trait PokerPlayerTrait extends HttpService {

  def shutdown()
  
  val playerRef: ActorRef

  def getVersion(): String

  val section = "(.+)=(.+)".r

  val myRoute =
    path("shutdown") {
      complete {
        shutdown()
        "ookay, going down"
      }
    } ~ path("") {
      entity(as[String]) { request =>
        val parts = request.split("&")
        parts(0) match {
          case section("action", action) => action match {
            case "check" => complete("We're here!")
            case "version" => complete(getVersion())
            case "bet_request" => bet_request(parts(1))
            case "showdown" => showdown
            case _ => complete("huh?")
          }
        }
      }
    }

  import spray.json._
  import domains.Server.CardDeserializer._
  import scala.concurrent.duration._
  import akka.pattern.ask
  implicit val tmo = Timeout(1.seconds)  
  def bet_request(game_state: String) = {
    val parts = game_state.split("=", 2)
    println(parts)
    if (parts(0) == "game_state") {
      val game_state = parts(1).parseJson.convertTo[GameState]
      playerRef ? ('place_bet, game_state)
    }
    val rand = Random.nextInt()

    complete(math.abs(rand).toString)
  }

  val showdown = {
    formFields('game_state) { game_state =>

      complete("bang")
    }
  }
}