package objektwerks

import munit._

case class Name(last: String, first: String)

given lastNameOrdering as Ordering[Name] = Ordering.by(_.last)
given firstNameOrdering as Ordering[Name] = Ordering.by(_.first)

class OrderingTest extends FunSuite {
  test("ordering") {
    val aName = Name(last = "a", first = "z")
    val zName = Name(last = "z", first = "a")
    val names = Seq( aName, zName )

    val lastNameSorted = names.sorted[Name](using lastNameOrdering)
    assert( lastNameSorted.head == aName)

    val firstNameSorted = names.sorted[Name](using firstNameOrdering)
    assert( firstNameSorted.head == zName)
 }
}