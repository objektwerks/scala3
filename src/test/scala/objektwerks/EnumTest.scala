package objektwerks

import munit._

enum Gate(state: Int) {
  case closed extends Gate(0)
  case open extends Gate(1)
}

class EnumTest extends FunSuite {
  test("gate") {
    val closed = Gate.closed
    assert(closed == Gate.closed)
    assert(closed.ordinal == 0)
    assert(Gate.valueOf("closed") == closed)

    val open = Gate.open
    assert(open == Gate.open)
    assert(open.ordinal == 1)
    assert(Gate.valueOf("open") == open)

    val values = Gate.values
    assert(values.length == 2)
    assert(values(0) == closed)
    assert(values(1) == open)
  }
}