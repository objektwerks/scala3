package objektwerks.macros

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.quoted.{Expr, Quotes}

/**
 * See: https://www.baeldung.com/scala/macros-scala-3
 */
final class MacroTest extends AnyFunSuite with Matchers:
  def oddOrEven(n: Expr[Int])(using Quotes): Expr[String] = '{
    $n % 2 match
      case 0 => "even"
      case _ => "odd"
  }


  test("macro"):
    println("todo")