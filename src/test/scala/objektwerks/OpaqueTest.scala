package objektwerks

import munit._

object Names {
  opaque type Name = String

  object Name {
    def apply(name: String): Name = name
  }
}

class OpaqueTest extends FunSuite {
  test("opaque") {
    import Names._

    val fredFlintstone: Name = Name("Fred Flintstone")
    assert( fredFlintstone == Name("Fred Flintstone") )
  }
}