package objektwerks

import org.scalatest.funsuite.AnyFunSuite

class Test extends AnyFunSuite {
  test("test") {
    assert(App.title.nonEmpty)
  }
}