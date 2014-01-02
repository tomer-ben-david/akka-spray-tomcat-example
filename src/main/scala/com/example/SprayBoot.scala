package com.example

/**
 * @author tomerb
 * date 1/2/14.
 */
import akka.actor.{Props, ActorSystem}
import spray.servlet.WebBoot
import akka.actor.ActorSystem
import akka.actor.Props


// this class is instantiated by the servlet initializer
// it needs to have a default constructor and implement
// the spray.servlet.WebBoot trait
class SprayBoot extends WebBoot {

  // we need an ActorSystem to host our application in
  val system = ActorSystem("actorsystem")

  // the service actor replies to incoming HttpRequests
  val serviceActor = system.actorOf(Props[ServiceActor])

}