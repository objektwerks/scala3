package objektwerks.traits

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TraitTest extends AnyFunSuite with Matchers:
  enum Honorific(honorific: String):
    case sir extends Honorific("Sir")
    case madam extends Honorific("Madam")

  enum Greeting(greeting: String):
    case goodMorning extends Greeting("I bid you good morning.")
    case goodEvening extends Greeting("I bid you good evening.")

  transparent trait SalutationBuilder:
    def build(honorific: Honorific, greeting: Greeting): String = s"$honorific, $greeting"

  sealed trait Salutation(val honorific: Honorific, val greeting: Greeting) extends SalutationBuilder:
    def greet(): String = build(honorific, greeting)

  final class Greeter(honorific: Honorific, greeting: Greeting) extends Salutation(honorific, greeting)

  test("trait") {
    import Greeting.*
    import Honorific.*

    val sirGoodMorningGreeter = Greeter(sir, goodMorning)
    sirGoodMorningGreeter.greet() shouldBe s"${sirGoodMorningGreeter.honorific}, ${sirGoodMorningGreeter.greeting}"

    val madamGoodEveningGreeter = Greeter(madam, goodEvening)
    madamGoodEveningGreeter.greet() shouldBe s"${madamGoodEveningGreeter.honorific}, ${madamGoodEveningGreeter.greeting}"
  }

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