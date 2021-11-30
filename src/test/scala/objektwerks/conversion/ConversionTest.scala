package objektwerks.conversion

import objektwerks.Person
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final case class Person(name: String):
  def greetings: String = s"Greetings. My name is $name."

given stringToPerson: Conversion[String, Person] with
  def apply(name: String): Person = Person(name)

class ConversionTest extends AnyFunSuite with Matchers:
  test("conversion") {
    import scala.language.implicitConversions

    "Fred Flintstone".greetings shouldBe "Greetings. My name is Fred Flintstone."
  }