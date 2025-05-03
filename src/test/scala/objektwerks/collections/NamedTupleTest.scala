package objektwerks.collections

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See:
 * 1. https://www.scala-lang.org/api/current/docs/docs/reference/experimental/named-tuples.html
 * 2. https://docs.scala-lang.org/sips/named-tuples.html
 */
final class NamedTupleTest extends AnyFunSuite with Matchers:
  test("named"):
    type Person = (name: String, age: Int)

    val fred: Person = (name = "Fred Flintstone", age = 68)

    fred.name shouldBe "Fred Flintstone"
    fred.age shouldBe 68

    fred match
      case (name, age) =>
        name shouldBe "Fred Flintstone"
        age shouldBe 68

    fred.toTuple._1 shouldBe "Fred Flintstone"
    fred.toTuple._2 shouldBe 68

    val pebbles: Person = (name = "Pebbles Flintstone", age = 16)
    val flintstones = List(fred, pebbles)

    val minors = flintstones.filter { person => person.age < 18 }
    minors.head shouldBe pebbles

    val seniors = flintstones
      .filter { person => person.age >= 18 }
      .map { person => NamedTuple.apply("name" -> person.name, "age" -> ( person.age + 1 )) }
    seniors.length shouldBe 1
    seniors.head.toTuple._1 shouldBe ("name", "Fred Flintstone")
    seniors.head.toTuple._2 shouldBe ("age", 69)

    val updatedFred = NamedTuple.apply("name" -> fred.name, "age" -> (fred.age + 1))
    updatedFred.toTuple._1 shouldBe ("name", "Fred Flintstone")
    updatedFred.toTuple._2 shouldBe ("age", 69)
    updatedFred match
      case (name, age) =>
        name._2 shouldBe "Fred Flintstone"
        age._2 shouldBe 69