package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 */
final class InlineTest extends AnyFunSuite with Matchers:
  // inline def and parameter
  inline def oddOrEven(inline n: Int): String = if n % 2 == 0 then "even" else "odd"

  test("inline"):
    oddOrEven(2) shouldBe "even"
    oddOrEven(1) shouldBe "odd"