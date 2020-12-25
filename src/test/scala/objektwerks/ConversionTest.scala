package objektwerks

import munit._

case class Person(name: String) {
  def greetings: String = s"Greetings. My name is $name."
}

given stringToPerson as Conversion[String, Person] {
  def apply(name: String): Person = Person(name)
}

class ConversionTest extends FunSuite {
  test("conversion") {
    import scala.language.implicitConversions

    assert("Fred Flintstone".greetings == "Greetings. My name is Fred Flintstone.")
  }
}