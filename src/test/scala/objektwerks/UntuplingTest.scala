package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class UntuplingTest extends AnyFunSuite with Matchers {
  test("untupling") {
    val tuples = List( (1, 1), (2, 2), (3, 3) )
    val sums = tuples.map {
      (x, y) => x + y
    }
    sums shouldBe List(2, 4, 6)
  }
}