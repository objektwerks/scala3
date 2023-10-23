package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 * See: https://softwaremill.com/scala-3-macros-tips-and-tricks/
 */
final class MacroTest extends AnyFunSuite with Matchers:
  import OddOrEvenMacro.*

  test("macro"):
    oddOrEven(2) shouldBe "even"
    oddOrEven(1) shouldBe "odd"