package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SelfTypeTest extends AnyFunSuite with Matchers:
  sealed trait Speaking:
    def speaking: String 

  sealed trait Hello extends Speaking:
    override def speaking = "hello" 

  sealed trait Goodbye extends Speaking:
    override def speaking = "goodbye" 

  class Speaker:
    self: Speaking =>
    def speak: String = speaking  

  test("self") {
    val helloSpeaker = new Speaker with Hello
    helloSpeaker.speak shouldEqual "hello"

    val goodbyeSpeaker = new Speaker with Goodbye
    goodbyeSpeaker.speak shouldEqual "goodbye"
  }