package objektwerks.types

import objektwerks.{Goodbye, Hello, Speaker, Speaking}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Speaking:
  def speaking: String 

sealed trait Hello extends Speaking:
  override def speaking = "hello" 

sealed trait Goodbye extends Speaking:
  override def speaking = "goodbye" 

class Speaker:
  self: Speaking =>
  def speak: String = speaking

class SelfTypeTest extends AnyFunSuite with Matchers:
  test("self") {
    val helloSpeaker = new Speaker with Hello
    helloSpeaker.speak shouldEqual "hello"

    val goodbyeSpeaker = new Speaker with Goodbye
    goodbyeSpeaker.speak shouldEqual "goodbye"
  }