package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.quoted.{Expr, Quotes}

object OddOrEvenMacro:
  def oddOrEvenQuotes(n: Expr[Int])(using Quotes): Expr[String] = '{
    $n % 2 match
      case 0 => "even"
      case _ => "odd"
  }

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 * See: https://softwaremill.com/scala-3-macros-tips-and-tricks/
 */
final class MacroTest extends AnyFunSuite with Matchers:
  import OddOrEvenMacro.*

  inline def oddOrEven(inline n: Int): String = ${ OddOrEvenMacro.oddOrEvenQuotes( 'n ) }

  test("macro"):
    oddOrEven(2) shouldBe "even"
    oddOrEven(1) shouldBe "odd"