package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.language.strictEquality
import scala.collection.mutable

opaque type MAC = String
type MACError = String

object MAC:
  val colon = ':'

  def apply(mac: String): Either[MACError, MAC] =
    Either.cond(
      mac.length == 12,
      mac,
      s"MAC length must be 12, not ${mac.length} for $mac."
    )

given CanEqual[MAC, MAC] = CanEqual.derived

extension (mac: MAC)
  def address: String = mac
  def display: String =
    import MAC.colon
    val chars = mac.toArray
    val builder = mutable.StringBuilder()
    builder += chars(0) += chars(1) += colon
    builder += chars(2) += chars(3) += colon
    builder += chars(4) += chars(5) += colon
    builder += chars(6) += chars(7) += colon
    builder += chars(8) += chars(9) += colon
    builder += chars(10) += chars(11)
    builder.toString
  def number: Long = java.lang.Long.parseLong(address, 16)

class OpaqueTypeTest extends AnyFunSuite with Matchers:
  private val address = "50ed3c45f4ba"

  test("valid mac") {
    MAC(address).foreach(a => a shouldBe address)
  }

  test("invalid mac") {
    MAC("1a2b3c").foreach(a => a shouldBe "")
  }

  test("display mac") {
    MAC(address).foreach(a => a.display shouldBe "50:ed:3c:45:f4:ba")
  }

  test("number mac") {
    MAC(address).foreach(a => a.number shouldBe 88979848688826L)
  }