package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

trait Init:
  def init: Boolean = true 

trait Run extends Init:
  def run: Boolean = init

class Runnable extends Run:
  def isRunning: Boolean = run 

trait Emotion:
  def isEmoting: Boolean = true 

trait Speach:
  def isSpeaking: Boolean = true 

class Robot extends Runnable with Emotion with Speach

class CompoundTypeTest extends AnyFunSuite with Matchers:
  test("compound") {
    val robot = Robot()
    robot.isRunning shouldBe true
    robot.isEmoting shouldBe true
    robot.isSpeaking shouldBe true
  }