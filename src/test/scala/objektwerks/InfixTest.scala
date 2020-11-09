package objektwerks

import munit._

import scala.annotation.infix

case class Amount(amount: Double) {
  def +(other: Amount): Amount = Amount(amount + other.amount)
  def -(other: Amount): Amount = Amount(amount - other.amount)
  @infix def add(other: Amount): Amount = this + other
  @infix def subtract(other: Amount): Amount = this - other
}

class InfixTest extends FunSuite {
  test("infix") {
    assert( Amount(1.0) + Amount(2.0) == Amount(3.0) )
    assert( Amount(3.0) - Amount(2.0) == Amount(1.0) )

    val added = Amount(1.0) add Amount(2.0)
    val subtracted = Amount(3.0) subtract Amount(2.0)
    assert( added == Amount(3.0) )
    assert( subtracted == Amount(1.0) )
    assert( (Amount(1.0) add Amount(2.0)) == Amount(3.0) )
    assert( (Amount(3.0) subtract Amount(2.0)) == Amount(1.0) )
  }
}