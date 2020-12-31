package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

case class Amount(value: Double) {
  def +(other: Amount): Amount = Amount(value + other.value)
  def -(other: Amount): Amount = Amount(value - other.value)
  infix def add(other: Amount): Amount = this + other
  infix def subtract(other: Amount): Amount = this - other
}

extension (amount: Amount)
  def discount(discount: Double): Amount = amount.copy(value = amount.value - (amount.value * discount))

class InfixTest extends AnyFunSuite with Matchers {
  test("infix") {
    Amount(1.0) + Amount(2.0) shouldBe Amount(3.0)
    Amount(3.0) - Amount(2.0) shouldBe Amount(1.0)

    (Amount(1.0) add Amount(2.0)) shouldBe Amount(3.0)
    (Amount(3.0) subtract Amount(2.0)) shouldBe Amount(1.0)

    Amount(3.0).discount(0.10) shouldBe Amount(2.7)
  }
}