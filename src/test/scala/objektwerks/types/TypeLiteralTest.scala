package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class TypeLiteralTest extends AnyFunSuite with Matchers:
  test("literal") {
    val n = 2
    val two: 2 = 2
    val pi: 3.14 = 3.14

    def bounce(n: Int): Int = n
    def bounceStrict(two: 2): Int = two

    bounce(n) shouldBe n
    bounce(two) shouldBe two
    bounceStrict(2) shouldBe n
    bounceStrict(two) shouldBe two
    3.14 shouldBe pi
  }