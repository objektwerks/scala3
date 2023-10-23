package objektwerks.classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class ClassTest extends AnyFunSuite with Matchers:
  sealed trait Car:
    def drive: String = "driving"
    def sound: String

  final case class Porsche(sound: String) extends Car
  final case class Corvette(sound: String) extends Car
  final case class Maserati(sound: String) extends Car

  object Owner:
    def startEngines: Set[Car] = Set( Porsche("prrrr"), Corvette("woosh"), Maserati("grrrr") )

  final class Human(val first: String, val last: String, val initial: String):
    def this(first: String, last: String) = this(first, last, "")

  final class Square:
    def apply(n: Int): Int = n * n

  object Cube:
    def apply(n: Int): Int = n * n * n

  final case class Timestamp(seconds: Int)

  object Timestamp:
    def apply(hours: Int, minutes: Int, seconds: Int): Timestamp = Timestamp( (hours * 60 * 60) + (minutes * 60) + seconds )

  test("classes with inheritance"):
    val cars = Owner.startEngines
    for (car <- cars)
      car.sound.nonEmpty shouldBe true
      car match
        case porsche: Porsche =>
          porsche.drive shouldEqual "driving"
          porsche.sound shouldEqual "prrrr"
        case corvette: Corvette =>
          corvette.drive shouldEqual "driving"
          corvette.sound shouldEqual "woosh"
        case maserati: Maserati =>
          maserati.drive shouldEqual "driving"
          maserati.sound shouldEqual "grrrr"

  test("constructors"):
    val primary = Human("fred", "flintstone", "r")
    val secondary = Human("barney", "rebel")
    primary.initial.nonEmpty shouldBe true
    secondary.initial.isEmpty shouldBe true

  test("class apply"):
    val square = Square()
    square(2) shouldEqual 4
    square.apply(3) shouldEqual 9

  test("object apply"):
    Cube(2) shouldEqual 8
    Cube.apply(3) shouldEqual 27

  test("companion object"):
    Timestamp(1, 1, 1).seconds shouldEqual 3661