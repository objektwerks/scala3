package scala

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class Test extends AnyFunSuite with Matchers {
  test("test") {
    val obtained = 3
    val expected = 3
    obtained shouldEqual expected
  }
}