package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class UnionTypeTest extends AnyFunSuite with Matchers:
  sealed trait Beer:
    val name: String

  final case class Lager(name: String) extends Beer:
    def prost: Lager = this

  final case class Pilsner(name: String) extends Beer:
    def cheers: Pilsner = this

  def drink(beer: Lager | Pilsner): Beer = beer match
    case lager @ Lager(_) => lager.prost
    case pilsner @ Pilsner(_) => pilsner.cheers

  test("union") {
    val lager = Lager("Song of Joy")
    val pilsner = Pilsner("Poetica 2")

    drink( lager ).isInstanceOf[Lager] shouldBe true
    drink( lager ).name shouldBe "Song of Joy"

    drink( pilsner ).isInstanceOf[Pilsner] shouldBe true
    drink( pilsner ).name shouldBe "Poetica 2"
  }