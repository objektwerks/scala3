package objektwerks.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Validator._

class ModelTest extends AnyFunSuite with Matchers:
  val store = Store()
  val service = Service(store)
  val dispatcher = Dispatcher(service)

  test("register") {
    val command = Register("test@test.com")
    dispatcher.dispatch(command) match {
      case Registered(account) => account.isActivated shouldBe true
      case _ => fail()
    }
  }