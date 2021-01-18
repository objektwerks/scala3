package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class OrderingTest extends AnyFunSuite with Matchers:
  final case class Name(last: String, first: String)

  given lastNameOrdering: Ordering[Name] = Ordering.by(_.last)
  given firstNameOrdering: Ordering[Name] = Ordering.by(_.first)
  
  test("ordering") {
    val aName = Name(last = "a", first = "z")
    val zName = Name(last = "z", first = "a")
    val names = Seq( aName, zName )

    val lastNameSorted = names.sorted[Name](using lastNameOrdering)
    lastNameSorted.head shouldBe aName

    val firstNameSorted = names.sorted[Name](using firstNameOrdering)
    firstNameSorted.head shouldBe zName
  }