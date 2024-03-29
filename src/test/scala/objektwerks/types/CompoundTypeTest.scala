package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class CompoundTypeTest extends AnyFunSuite with Matchers:
  sealed trait Boot:
    def boot: Boolean = true

  sealed trait Greet:
    def greet(name: String): String = s"Greetings, $name!"

  sealed trait Shutdown:
    def shutdown: Boolean = true 

  final class Robot extends Boot with Greet with Shutdown

  test("compound"):
    val robot = Robot()
    robot.boot shouldBe true
    robot.greet("Barney") shouldBe "Greetings, Barney!"
    robot.shutdown shouldBe true