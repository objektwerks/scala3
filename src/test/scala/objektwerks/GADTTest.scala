package objektwerks

import munit._

sealed trait Expression[T]
final case class IntExpr(int: Int) extends Expression[Int]
final case class BoolExpr(boolean: Boolean) extends Expression[Boolean]

def eval[T](expression: Expression[T]): T = expression match {
  case IntExpr(int) => int
  case BoolExpr(boolean) => boolean
}

class GADTTest extends FunSuite {
  test("gadt") {
    assert( eval( IntExpr(3) ) == 3 )
    assert( eval( BoolExpr(true) ) == true )
  }
}