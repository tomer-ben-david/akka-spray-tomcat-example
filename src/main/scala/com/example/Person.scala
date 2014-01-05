package com.example

import spray.json.DefaultJsonProtocol

/**
 * @author tomerb
 * date 1/5/14.
 */
case class Person(val name: String)

object JsonImplicits extends DefaultJsonProtocol {
  implicit val impPerson = jsonFormat1(Person)
}

