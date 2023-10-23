package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.quoted.{Expr, Quotes}

object OddOrEven:
  def oddOrEvenQuotes(n: Expr[Int])(using Quotes): Expr[String] = '{
    $n % 2 match
      case 0 => "even"
      case _ => "odd"
  }

  inline def oddOrEvenSplicing(inline n: Int): String = ${ oddOrEvenQuotes( 'n ) }

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 */
final class MacroTest extends AnyFunSuite with Matchers:
  test("macro"):
    println("todo")