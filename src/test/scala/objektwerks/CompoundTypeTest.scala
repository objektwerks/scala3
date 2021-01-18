package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Boot:
  def boot: Boolean = true

sealed trait Walk:
  def walk: Boolean = true

sealed trait Stop:
  def stop: Boolean = true 

sealed trait Shutdown:
  def shutdown: Boolean = true 

final class Robot extends Boot with Walk with Stop with Shutdown

class CompoundTypeTest extends AnyFunSuite with Matchers:
  test("compound") {
    val robot = Robot()
    robot.boot shouldBe true
    robot.walk shouldBe true
    robot.stop shouldBe true
    robot.shutdown shouldBe true
  }