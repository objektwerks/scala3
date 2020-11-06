package objektwerks

import munit._

trait Beer {
  val name: String
}

case class Lager(name: String) extends Beer {
  def prost: Lager = this
}
case class Pilsner(name: String) extends Beer {
  def cheers: Pilsner = this
}

def drink(beer: Lager | Pilsner): Beer = beer match {
  case lager @ Lager(name) => lager.prost
  case pilsner @ Pilsner(name) => pilsner.cheers
}

class UnionTypeTest extends FunSuite {
  test("union") {
    assert( drink( Lager("Son of Joy") ).isInstanceOf[Lager] )
    assert( drink( Lager("Song of Joy") ).name == "Song of Joy" )

    assert( drink( Pilsner("Poetica 2") ).isInstanceOf[Pilsner] )
    assert( drink( Lager("Poetica 2") ).name == "Poetica 2" )
  }
}