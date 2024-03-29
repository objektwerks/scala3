package objektwerks.traits

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class TraitTest extends AnyFunSuite with Matchers:
  enum Honorific(val honorific: String):
    case sir extends Honorific("Sir")
    case madam extends Honorific("Madam")

  enum Greeting(val greeting: String):
    case goodMorning extends Greeting("I bid you good morning.")
    case goodEvening extends Greeting("I bid you good evening.")

  sealed trait Salutation(val honorific: Honorific, val greeting: Greeting):
    def greet(): String = s"$honorific, $greeting"

  final class Greeter(honorific: Honorific, greeting: Greeting) extends Salutation(honorific, greeting)

  test("trait") {
    import Greeting.*
    import Honorific.*

    val sirGoodMorningGreeter = Greeter(sir, goodMorning)
    sirGoodMorningGreeter.greet() shouldBe s"${sirGoodMorningGreeter.honorific}, ${sirGoodMorningGreeter.greeting}"

    val madamGoodEveningGreeter = Greeter(madam, goodEvening)
    madamGoodEveningGreeter.greet() shouldBe s"${madamGoodEveningGreeter.honorific}, ${madamGoodEveningGreeter.greeting}"
  }

  sealed trait Speaker(val topic: String):
    def speak(): String

  sealed trait BoringSpeaker extends Speaker
  sealed trait ExcitingSpeaker extends Speaker

  final class Teacher(name: String) extends Speaker("math"):
    override def speak(): String = s"$name: 1 + 2 = 3"
  
  final class Professor(name: String) extends BoringSpeaker with Speaker("biology"):
    override def speak(): String = s"$name: your cells are powered by structured water"

  final class Coach(name: String) extends ExcitingSpeaker with Speaker("football"):
    override def speak(): String = s"$name: go team go!"

  test("constructor"):
    val teacher = Teacher("fred flintstone")
    teacher.topic shouldBe "math"
    teacher.speak() shouldBe "fred flintstone: 1 + 2 = 3"

    val professor = Professor("wilma flintstone")
    professor.topic shouldBe "biology"
    professor.speak() shouldBe "wilma flintstone: your cells are powered by structured water"

    val coach = Coach("barney rebel")
    coach.topic shouldBe "football"
    coach.speak() shouldBe "barney rebel: go team go!"