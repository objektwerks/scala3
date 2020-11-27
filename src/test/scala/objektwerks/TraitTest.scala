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

transparent trait SalutationBuilder {
  def build(honorific: Honorific, greeting: Greeting): String = s"$honorific, $greeting"
}

sealed trait Salutation(val honorific: Honorific, val greeting: Greeting) extends SalutationBuilder {
  def greet(): String = build(honorific, greeting)
}

class Greetor(honorific: Honorific, greeting: Greeting) extends Salutation(honorific, greeting)

class TraitTest extends FunSuite {
  test("trait") {
    import Honorific._
    import Greeting._

    val maleGreetor = Greetor(sir, goodMorning)
    assert( maleGreetor.greet() == s"${maleGreetor.honorific}, ${maleGreetor.greeting}" )

    val femaleGreetor = Greetor(madam, goodEvening)
    assert( femaleGreetor.greet() == s"${femaleGreetor.honorific}, ${femaleGreetor.greeting}" )
  }
}