package objektwerks.traits

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TraitTest extends AnyFunSuite with Matchers:
  enum Honorific(honorific: String):
    case sir extends Honorific("Sir")
    case madam extends Honorific("Madam")

  enum Greeting(greeting: String):
    case goodMorning extends Greeting("I bid you good morning.")
    case goodEvening extends Greeting("I bid you good evening")

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