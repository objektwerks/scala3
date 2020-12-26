package objektwerks

import munit._

class AppTest extends FunSuite {
  test("app") {
    assert( App.title.nonEmpty == true )
  }
}