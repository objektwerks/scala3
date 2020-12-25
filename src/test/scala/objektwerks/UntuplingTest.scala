package objektwerks

import munit._

class UntuplingTest extends FunSuite {
  test("untupling") {
    val tuples = List( (1, 1), (2, 2), (3, 3) )
    val sums = tuples.map {
      (x, y) => x + y
    }
    assert( sums == List(2, 4, 6) )
  }
}