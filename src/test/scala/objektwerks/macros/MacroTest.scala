package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.quoted.{Expr, Quotes}

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 */
final class MacroTest extends AnyFunSuite with Matchers:
  def oddOrEven(n: Expr[Int])(using Quotes): Expr[String] =
    val i = n.valueOrAbort
    i % 2 match
      case 0 => Expr("even")
      case _ => Expr("odd")

  test("macro"):
    import Quotes.given
    oddOrEven(Expr(2))(using Quotes) shouldBe Expr("even")
    oddOrEven(Expr(1))(using Quotes) shouldBe Expr("odd")