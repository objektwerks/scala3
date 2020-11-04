package objektwerks

import munit._

class Test extends FunSuite {
  test("test") {
    assert(App.title.nonEmpty)
  }
}