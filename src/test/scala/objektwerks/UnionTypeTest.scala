package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Beer:
  val name: String

final case class Lager(name: String) extends Beer:
  def prost: Lager = this

final case class Pilsner(name: String) extends Beer:
  def cheers: Pilsner = this

def drink(beer: Lager | Pilsner): Beer = beer match
  case lager @ Lager(name) => lager.prost
  case pilsner @ Pilsner(name) => pilsner.cheers

class UnionTypeTest extends AnyFunSuite with Matchers {
  test("union") {
    val lager = Lager("Song of Joy")
    val pilsner = Pilsner("Poetica 2")

    drink( lager ).isInstanceOf[Lager] shouldBe true
    drink( lager ).name shouldBe "Song of Joy"

    drink( pilsner ).isInstanceOf[Pilsner] shouldBe true
    drink( pilsner ).name shouldBe "Poetica 2"
  }
}