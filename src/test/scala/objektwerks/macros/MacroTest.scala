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

/* Test passes with these warnings:
  warn] -- [E030] Match case Unreachable Warning: /Users/tripletail/workspace/scala3/src/
                                      test/scala/objektwerks/macros/MacroTest.scala:14:13 
  [warn] 14 |    oddOrEven(2) shouldBe "even" // unreachable case warning
  [warn]    |    ^^^^^^^^^^^^
  [warn]    |    Unreachable case
  [warn]    |----------------------------------------------------------------------------
  [warn]    |Inline stack trace
  [warn]    |- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
  [warn]    |This location contains code that was inlined from OddOrEvenMacro.scala:9
  [warn]  9 |      case _ => "odd"
  [warn]    |           ^
  [warn]    |- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
  [warn]    |This location contains code that was inlined from OddOrEvenMacro.scala:9
  [warn]  7 |    $n % 2 match
  [warn]    |    ^
  [warn]  8 |      case 0 => "even"
  [warn]  9 |      case _ => "odd"
  [warn]     ----------------------------------------------------------------------------
*/