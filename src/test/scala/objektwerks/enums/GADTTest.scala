package objektwerks.enums

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class GADTTest extends AnyFunSuite with Matchers:
  sealed trait Expression[T]
  final case class IntExpr(int: Int) extends Expression[Int]
  final case class BoolExpr(boolean: Boolean) extends Expression[Boolean]

  def eval[T](expression: Expression[T]): T = expression match
    case IntExpr(int) => int
    case BoolExpr(boolean) => boolean
    case _ => fail()

  enum Box[T](content: T):
    case IntBox(number: Int) extends Box[Int](number)
    case BoolBox(boolean: Boolean) extends Box[Boolean](boolean)

    def unbox: T = content

  test("trait > case class > gadt") {
    eval( IntExpr(3) ) shouldBe 3
    eval( BoolExpr(true) ) shouldBe true
  }

  test("enum > gadt") {
    import Box.*
    IntBox(3).unbox shouldBe 3
    BoolBox(true).unbox shouldBe true
  }