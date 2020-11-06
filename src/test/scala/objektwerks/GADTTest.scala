package objektwerks

import munit._

trait Expr[T]
case class IntExpr(i: Int) extends Expr[Int]
case class BoolExpr(b: Boolean) extends Expr[Boolean]

def eval[T](e: Expr[T]): T = e match {
  case IntExpr(i) => i
  case BoolExpr(b) => b
}

class GADTTest extends FunSuite {
  test("gadt") {
    assert( eval( IntExpr(3) ) == 3 )
    assert( eval( BoolExpr(true) ) == true )
 }
}