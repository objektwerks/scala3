package objektwerks.collections

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See: https://www.scala-lang.org/api/current/docs/docs/reference/experimental/named-tuples.html
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
