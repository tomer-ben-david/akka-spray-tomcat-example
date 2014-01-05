package com.example

import scala.concurrent.ExecutionContext.Implicits.global
import spray.http._
import MediaTypes._
import akka.actor.Actor
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.json.{JsString, JsValue, RootJsonFormat, DefaultJsonProtocol}
import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
import spray.routing.Directive.pimpApply
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing.authentication.BasicAuth
import spray.routing.authentication.UserPass
import spray.routing.authentication.UserPassAuthenticator
import spray.routing.authentication.UserPassAuthenticator
import spray.routing.directives.AuthMagnet.fromContextAuthenticator
import spray.routing.directives.FieldDefMagnet.apply

/**
 * @author tomerb
 * date 1/2/14.
 */

// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class ServiceActor extends Actor with Service {


  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}

// this trait defines our service behavior independently from the service actor
trait Service extends HttpService {
//  implicit object OrderJsonFormat extends RootJsonFormat[Order] {
//    def write(obj: Order): JsValue = JsString(obj.toString)
//
//    def read(json: JsValue): Order = json match {
//      case JsString(str) => Order(str.toInt)
//    }
//  }

  // Very important don't forget to import the implicists otherwise it would not recognize the parsing of your json!
  import com.example.Person
  import com.example.PersonJsonImplicits._

  val myRoute =
    path("") {
//      GET example
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>Jetty</i>!</h1>
              </body>
            </html>
          }

        }
      }
    } ~
    path("orders") {
      post {
        // note you must have import spray.httpx.SprayJsonSupport.sprayJsonMarshaller
        // import spray.httpx.SprayJsonSupport.sprayJsonUnmarshaller
        // for following unmarshaling marshaling to work!
        entity(as[Person]) { person =>
          complete {
            // ... write order to DB
            person
          }
        }
      }
    }

}

