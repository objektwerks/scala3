package objektwerks

import munit._

object Names {
  opaque type Name = String

  def toName(value: String): Name = value
}

class OpaqueTest extends FunSuite {
  test("opaque") {
    import Names._

    val fredFlintstone: Name = toName("Fred Flintstone")
    assert( fredFlintstone == toName("Fred Flintstone") )
  }
}