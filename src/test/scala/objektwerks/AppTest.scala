package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class AppTest extends AnyFunSuite with Matchers {
  test("app") {
    App.title.nonEmpty shouldBe true
  }
}