package objektwerks.classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class CaseClassTest extends AnyFunSuite with Matchers:
  // Algebraic Data Types ( ADTs )
  sealed abstract class Command // Sum Type, Move and Rotate is a Command
  object Command:
    final case class Move(meters: Int) extends Command // Product Type, Move has meters
    final case class Rotate(degrees: Int) extends Command // Product Type, Rotate has degrees

    def handle(command: Command): String = command match
      case Move(meters)    => s"Moving by $meters meter(s)."
      case Rotate(degrees) => s"Rotating by $degrees degree(s)."

  // ADT Sum Type Pattern - Is-A, Inheritence - Tiger, Panther and Bear is an Animal
  sealed trait Animal:
    def speak: String

  // ADT Product Type Pattern - Has-A, Composition
  final case class Tiger(speech: String) extends Animal: // Tiger is an Animal, has speech
    override def speak: String = speech

  final case class Panther(speech: String) extends Animal: // Panther is an Animal, has speech
    override def speak: String = speech

  final case class Bear(speech: String) extends Animal: // Bear is an Animal, has speech
    override def speak: String = speech

  case object ZooKeeper:
    def openCages: Set[Animal] = Set(Tiger("prrrr"), Panther("woosh"), Bear("grrrr"))

  // Value Classes ( See types.OpaqueTypeTest )
  final case class Meter(value: Double):
    def toFeet: Foot = Foot(value * 0.3048)

  final case class Foot(value: Double):
    def toMeter: Meter = Meter(value / 0.3048)

  test("adt") {
    import Command.*

    handle( Move(1) ) shouldBe "Moving by 1 meter(s)."
    handle( Rotate(2)) shouldBe "Rotating by 2 degree(s)."
  }

  test("case class"):
    val animals = ZooKeeper.openCages
    for (animal <- animals)
      animal.speak.nonEmpty shouldBe true
      animal match
        case Tiger(speech) => speech shouldEqual "prrrr"
        case Panther(speech) => speech shouldEqual "woosh"
        case Bear(speech) => speech shouldEqual "grrrr"

  test("equality"):
    val tiger1 = Tiger("roar")
    val tiger2 = Tiger("roar")
    val tiger3 = Tiger("prrrr")
    tiger1 shouldEqual tiger2
    tiger1 should not equal tiger3
    tiger2 should not equal tiger3

  test("copy"):
    val panther1 = Panther("prrrr")
    val panther2 = panther1.copy(speech = "arrrgh")
    panther1 shouldEqual panther1.copy()
    panther1 should not equal panther2

  test("toString"):
    val bear1 = Bear("grrrr")
    val bear2 = Bear("grrrr")
    bear1.toString shouldEqual bear2.toString

  test("apply unapply"):
    val tiger1 = Tiger("roar")
    tiger1 shouldEqual Tiger.apply(tiger1.speak)
    Tiger.unapply(tiger1) shouldEqual Tiger("roar")

  test("value classes"):
    Meter(3.0).toFeet shouldEqual Foot(0.9144000000000001)
    Foot(3.0).toMeter shouldEqual Meter(9.84251968503937)