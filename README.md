akka-spray-tomcat-example
=========================

akka spray tomcat example in scala

# Having serialization done by json spray for you

Lets say you have the following case class (important its a case class for regular classes you will need something a little different)

```scala
case class Person(val name: String)
```

and you want json-spray to serialize it you will need the define the following implicit
you can just define it beside your case class in the same file.

```scala
object PersonJsonImplicits extends DefaultJsonProtocol {
  implicit val impPerson = jsonFormat1(Person)
}
```

note the *1* in jsonFormat this means your case class has single parameter if it had 2  you would use... jsonFormat2 :)