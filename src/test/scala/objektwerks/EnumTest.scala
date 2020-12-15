package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

enum Gate(state: Int) {
  case closed extends Gate(0)
  case open extends Gate(1)
}

class EnumTest extends AnyFunSuite with Matchers {
  test("gate") {
    val closed = Gate.closed
    closed shouldBe Gate.closed
    closed.ordinal shouldBe 0
    Gate.valueOf("closed") shouldBe closed

    val open = Gate.open
    open shouldBe Gate.open
    open.ordinal shouldBe 1
    Gate.valueOf("open") shouldBe open

    val values = Gate.values
    values.length shouldBe 2
    values(0) shouldBe closed
    values(1) shouldBe open
  }
}