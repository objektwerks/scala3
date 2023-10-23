package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 * See: https://softwaremill.com/scala-3-macros-tips-and-tricks/
 */
final class MacroTest extends AnyFunSuite with Matchers:
  test("macro"):
    import OddOrEvenMacro.*

    oddOrEven(2) shouldBe "even" // unreachable case warning
    oddOrEven(1) shouldBe "odd"