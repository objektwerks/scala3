package objektwerks

import munit._

enum NumberType {
  case negative extends NumberType
  case positive extends NumberType
  case zero extends NumberType
}

import NumberType._
def typeOfNumber(number: Int): NumberType =
  if number < 0 then
    negative
  else if number == 0 then
    zero
  else
    positive

class ControlTest extends FunSuite {
  test("if then else") {
    import NumberType._

    assert( typeOfNumber(-1) == negative)
    assert( typeOfNumber(0) == zero)
    assert( typeOfNumber(1) == positive)

    inline val x = 1
    val result = if x < 0 then -x else x
    assert( result == x )
  }

  test("for > yield") {
    val xs = -1 to 3
    val result = for x <- xs if x > 0
    yield x * x
    assert( result.sum == 14 )
  }

  test("for > do") {
    val xs = 1 to 3
    val ys = 4 to 6
    for
      x <- xs
      y <- ys
      z = x + y
    do
      assert( z >= 5 && z <= 9)
  }

  test("while > do") {
    var x = 3
    while x > 0
    do x = x - 1
    assert( x == 0 )
  }
}