package objektwerks.collections

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class NamedTupleTest extends AnyFunSuite with Matchers:
  type Person = (name: String, age: Int)

  test("named"):
    val fred = (name = "Fred Flintstone", age = 68)
    fred.name shouldBe "Fred Flintstone"
    fred.age shouldBe 68
