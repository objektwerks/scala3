package objektwerks

import munit._

opaque type Name = String
object Name {
  def apply(name: String): Name = name
}

class OpaqueTest extends FunSuite {
  test("opaque") {
    val fredFlintstone = Name("Fred Flintstone")
    assert( fredFlintstone == Name("Fred Flintstone") )
  }
}