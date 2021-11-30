package objektwerks.control

import objektwerks.NumberType
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

enum NumberType:
  case negative, positive, zero

import objektwerks.control.NumberType.*
def typeOfNumber(number: Int): NumberType =
  if number < 0 then
    negative
  else if number == 0 then
    zero
  else
    positive

class ControlTest extends AnyFunSuite with Matchers:
  test("if then else") {
    import NumberType.*

    typeOfNumber(-1) shouldBe negative
    typeOfNumber(0) shouldBe zero
    typeOfNumber(1) shouldBe positive

    val x = 1
    val result = if x < 0 then -x else x
    result shouldBe x
  }

  test("for > guard > yield") {
    val xs = -1 to 3
    val result = for x <- xs if x > 0 yield x * x
    result.sum shouldBe 14
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
    while x > 0 do x = x - 1
    x shouldBe 0
  }