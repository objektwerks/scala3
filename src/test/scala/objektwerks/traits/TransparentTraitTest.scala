package objektwerks.traits

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://www.baeldung.com/scala/transparent-traits
  */
class TransparentTraitTest extends AnyFunSuite with Matchers:
  sealed transparent trait Marker
  sealed trait Entity
  final case class Person(name: String, age: Int) extends Entity, Marker

  test("transparent"):    
    val persons = Set( Person("fred", 25), Person("barney", 28) )
    persons.size shouldBe 2