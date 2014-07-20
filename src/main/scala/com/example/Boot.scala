package leanPoker.scalaPlayer

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("on-spray-can")

  val player = system.actorOf(Props[PlayerActor])
  
  // create and start our service actor
  val service = system.actorOf(LeanPokerCommunicationActor.props(player), "demo-service")

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, interface = "0.0.0.0", port = 12345)
}