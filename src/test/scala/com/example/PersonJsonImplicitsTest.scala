package com.example

import org.scalatest.FlatSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.example.domain.{Animal, Person}
import spray.json._
import DefaultJsonProtocol._
import com.example.util.json.PersonSerializationImplicits.personFormat
import com.example.util.json.PersonSerializationImplicits.PersonJsonImplicits

/**
 * @author tomerb
 * date 1/6/14.
 */
@RunWith(classOf[JUnitRunner])
class PersonJsonImplicitsTest extends FlatSpec with ShouldMatchers {

  "PersonJsonImplicits" should "be able to convert person to json - sanity" in {
    new Person("somename").toJson.toString should equal(s"""{"name":"somename","kind":"person"}""")
  }

  "PersonJsonImplicits" should "be able to convert trait to  json" in {

    val myAnimal: Animal = new Person("somename")
    myAnimal.toJson.toString should equal("""{"name":"somename","kind":"person"}""")
  }
}
