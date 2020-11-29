package objektwerks

import munit._

import scala.util.Try

val divide: PartialFunction[Int, Int] = {
  case d: Int if d != 0 => d / 1
}

val divideExt = new PartialFunction[Int, Int] {
  def apply(i: Int): Int = i / 1
  def isDefinedAt(i: Int): Boolean = i != 0
}

val isEven: PartialFunction[Int, String] = {
  case x if x % 2 == 0 => s"$x is even"
}

val isOdd: PartialFunction[Int, String] = {
  case x if x % 2 == 1 => s"$x is odd"
}

class PartialFunctionTest extends FunSuite {
  test("partial function") {
    assert( ( Try { List(0, 1, 2) map divide }.isFailure ) == true )
    assert( ( List(0, 1, 2) collect divide ) == List(1, 2) )
    assert( ( List(42, "cat") collect { case i: Int => divide(i) } ) == List(42) )
  }
}