package objektwerks.`macro`

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
 * See:
 * 1. https://www.baeldung.com/scala/macros-scala-3
 * 2. https://softwaremill.com/scala-3-macros-tips-and-tricks/
 * 3. https://macros.scala.school/
 */
final class MacroTest extends AnyFunSuite with Matchers:
  test("macro"):
    oddOrEven(2) shouldBe "even"
    oddOrEven(1) shouldBe "odd"