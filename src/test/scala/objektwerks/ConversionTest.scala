package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

case class Person(name: String) {
  def greetings: String = s"Greetings. My name is $name."
}

given stringToPerson as Conversion[String, Person] {
  def apply(name: String): Person = Person(name)
}

class ConversionTest extends AnyFunSuite with Matchers {
  test("conversion") {
    import scala.language.implicitConversions
    
    "Fred Flintstone".greetings shouldBe "Greetings. My name is Fred Flintstone."
  }
}