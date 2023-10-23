package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 */
final class InlineTest extends AnyFunSuite with Matchers:
  // inline def and parameter
  inline def oddOrEven(inline n: Int): String =
    inline n % 2 match // inline to remove unreachable match error
      case 0 => "even"
      case 1 => "odd"

  test("inline"):
    // injected code detailed below
    oddOrEven(2) shouldBe "even" // if 2 % 2 == 0 then "even" else "odd"
    oddOrEven(1) shouldBe "odd"  // if 1 % 2 == 0 then "even" else "odd"