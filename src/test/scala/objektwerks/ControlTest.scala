package objektwerks

import munit._

object NumberType {
  inline val negative = "negative"
  inline val positive = "positive"
  inline val zero = "zero"
}
def typeOfNumber(number: Int): String = {
  import NumberType._

  if number < 0 then
    negative
  else if number == 0 then
    zero
  else
    positive
}

class ControlTest extends FunSuite {
  test("if then else") {
    import NumberType._

    assert( typeOfNumber(-1) == negative)
    assert( typeOfNumber(0) == zero)
    assert( typeOfNumber(1) == positive)
  }
}