package objektwerks

import munit._

enum Honorific(honorific: String) {
  case sir extends Honorific("Sir")
  case madam extends Honorific("Madam")
}

enum Greeting(greeting: String) {
  case goodMorning extends Greeting("I bid you good morning.")
  case goodEvening extends Greeting("I bid you good evening")
}

sealed trait Salutation(val honorific: Honorific) {
  def greet(greeting: Greeting): String = s"$honorific, $greeting"
}

final case class MaleGreeting(greeting: Greeting) extends Salutation(Honorific.sir)
final case class FemaleGreeting(greeting: Greeting) extends Salutation(Honorific.madam)

class TraitTest extends FunSuite {
  test("trait") {
    import Greeting._

    val maleGreeting = MaleGreeting(goodMorning)
    assert( maleGreeting.greet(maleGreeting.greeting) == s"${maleGreeting.honorific}, ${maleGreeting.greeting}" )

    val femaleGreeting = FemaleGreeting(goodEvening)
    assert( femaleGreeting.greet(femaleGreeting.greeting) == s"${femaleGreeting.honorific}, ${femaleGreeting.greeting}" )
  }
}