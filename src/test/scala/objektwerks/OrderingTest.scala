package objektwerks

import munit._

case class Name(last: String, first: String)

given lastNameOrdering as Ordering[Name] = Ordering.by(_.last)
given firstNameOrdering as Ordering[Name] = Ordering.by(_.first)

class OrderingTest extends FunSuite {
  test("ordering") {
    val aName = Name("alast", "zfirst")
    val zName = Name("zlast", "afirst")
    val names = Seq( aName, zName )

    val lastNameSorted = names.sorted[Name](using lastNameOrdering)
    assert( lastNameSorted.head == aName)

    val firstNameSorted = names.sorted[Name](using firstNameOrdering)
    assert( firstNameSorted.head == zName)
 }
}