package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TypesTest extends AnyFunSuite with Matchers:
  test("alias") {
    type User = String
    type Age = Int
    val users =  Map[User, Age]("john" -> 21, "jane" -> 19)

    users("john") shouldEqual 21
    users("jane") shouldEqual 19
  }

  test("literal") {
    val n = 2
    val two: 2 = 2
    val pi: 3.14 = 3.14

    def bounce(n: Int): Int = n
    def bounceStrict(two: 2): Int = n

    bounce(n) shouldBe n
    bounce(two) shouldBe two
    // bounceStrict(n) shouldBe n Found: (n : Int) Required: (2 : Int)
    bounceStrict(two) shouldBe two
    3.14 shouldBe pi
  }