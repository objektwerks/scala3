package objektwerks.conversion

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
    
import scala.language.implicitConversions

final class ConversionTest extends AnyFunSuite with Matchers:
  final case class Person(name: String):
    def greetings: String = s"Greetings. My name is $name."

  given Conversion[String, Person] with
    def apply(name: String): Person = Person(name)

  test("conversion") {
    "Fred Flintstone".greetings shouldBe "Greetings. My name is Fred Flintstone."
  }