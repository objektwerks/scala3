package objektwerks.conversion

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ConversionTest extends AnyFunSuite with Matchers:
  case class Person(name: String):
    def greetings: String = s"Greetings. My name is $name."

  given stringToPerson: Conversion[String, Person] with
    def apply(name: String): Person = Person(name)

  test("conversion") {
    import scala.language.implicitConversions

    "Fred Flintstone".greetings shouldBe "Greetings. My name is Fred Flintstone."
  }