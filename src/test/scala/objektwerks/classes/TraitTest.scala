package objektwerks.classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TraitTest extends AnyFunSuite with Matchers:
  trait Speaker(val topic: String):
    def speak(): String

  trait BoringSpeaker extends Speaker
  trait ExcitingSpeaker extends Speaker

  class Teacher(name: String) extends Speaker("math"):
    override def speak(): String = "1 + 2 = 3"
  
  class Professor(name: String) extends BoringSpeaker with Speaker("biology"):
    override def speak(): String = "your cells are powered by structured water"

  class Coach(name: String) extends ExcitingSpeaker with Speaker("football"):
    override def speak(): String = "go team go!"

  test("constructor") {
    val teacher = Teacher("fred flintstone")
    teacher.topic shouldBe "math"
    teacher.speak() shouldBe "1 + 2 = 3"

    val professor = Professor("wilma flintstone")
    professor.topic shouldBe "biology"
    professor.speak() shouldBe "your cells are powered by structured water"

    val coach = Coach("barney rebel")
    coach.topic shouldBe "football"
    coach.speak() shouldBe "go team go!"
  }

  test("super") {

  }