package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Expression[T]
final case class IntExpr(int: Int) extends Expression[Int]
final case class BoolExpr(boolean: Boolean) extends Expression[Boolean]

def eval[T](expression: Expression[T]): T = expression match
  case IntExpr(int) => int
  case BoolExpr(boolean) => boolean

enum Box[T](content: T):
  def unbox: T = content

  case IntBox(number: Int) extends Box[Int](number)
  case BoolBox(boolean: Boolean) extends Box[Boolean](boolean)
end Box // end Box is optional!!!  

class GADTTest extends AnyFunSuite with Matchers:
  test("gadt") {
    eval( IntExpr(3) ) shouldBe 3
    eval( BoolExpr(true) ) shouldBe true

    import Box._
    IntBox(3).unbox shouldBe 3
    BoolBox(true).unbox shouldBe true
  }