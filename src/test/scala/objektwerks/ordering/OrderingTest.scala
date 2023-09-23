package objektwerks.ordering

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class OrderingTest extends AnyFunSuite with Matchers:
  final case class Name(last: String, first: String)

  given lastOrdering: Ordering[Name] = Ordering.by(_.last)
  given firstOrdering: Ordering[Name] = Ordering.by(_.first)

  test("ordering > sorted"):
    val aName = Name(last = "a", first = "z")
    val zName = Name(last = "z", first = "a")
    val names = Seq( aName, zName )

    val lastNameSorted = names.sorted[Name](using lastOrdering)
    lastNameSorted.head shouldBe aName

    val firstNameSorted = names.sorted[Name](using firstOrdering)
    firstNameSorted.head shouldBe zName