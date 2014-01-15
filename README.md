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

Then you can use toJson on your pimped class:
** You must import spray.json._ otherwise `toJson` won't be recognized! **
```scala
import spray.json._
new Person("somename").toJson
```

note the `1` in `jsonFormat1` this means your case class has single parameter if it had 2  you would use... `jsonFormat2` :)

# Being async
now if you want to handle your request in an async way you don't really need to create actors
spray can do that for you just wrap your handling with `detach {` as following:

```scala
       detach() { // this make the below operation async (note for your app to really be async you should  not block the underlying thread!)
          respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
            complete {
              <html>
```
Note that although we called `detach()` if you have inside the code which detach calls some blocking code
this would still mean it would block the thread on which detach is running therefore if you do any blocking `get` you should
still wrap it in a future and have a callback for it (same as you should not block from within handling of a message in an actor).