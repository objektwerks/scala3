package objektwerks.enums

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Expr[T]
final case class IntExpr(int: Int) extends Expr[Int]
final case class BoolExpr(boolean: Boolean) extends Expr[Boolean]

def eval[T](expr: Expr[T]): T =
  expr match
    case IntExpr(int) => int
    case BoolExpr(boolean) => boolean

enum Box[T](content: T):
  case IntBox(number: Int) extends Box[Int](number)
  case BoolBox(boolean: Boolean) extends Box[Boolean](boolean)

  def unbox: T = content

class GenericEnumTest extends AnyFunSuite with Matchers:
  test("trait > case class > gadt") {
    eval( IntExpr(3) ) shouldBe 3
    eval( BoolExpr(true) ) shouldBe true
  }

  test("enum > gadt") {
    import Box.*
    
    IntBox(3).unbox shouldBe 3
    BoolBox(true).unbox shouldBe true
  }