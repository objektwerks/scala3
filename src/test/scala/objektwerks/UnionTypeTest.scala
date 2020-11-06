package objektwerks

import munit._

trait Drink {
  def drink(): Beer
}

trait Beer {
  val name: String
  def drink: Beer = this
}

case class Lager(name: String) extends Beer
case class Pilsner(name: String) extends Beer

def drink(beer: Lager | Pilsner): Beer =
  beer match {
    case lager @ Lager(name) => lager.drink
    case pilsner @ Pilsner(name) => pilsner.drink
  }

class UnionTypeTest extends FunSuite {
  test("union") {
    assert( drink( Lager("Son of Joy") ).isInstanceOf[Lager] )
    assert( drink( Lager("Song of Joy") ).name == "Song of Joy" )

    assert( drink( Pilsner("Poetica 2") ).isInstanceOf[Pilsner] )
    assert( drink( Lager("Poetica 2") ).name == "Poetica 2" )
  }
}