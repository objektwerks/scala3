package objektwerks.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Validator._

class ModelTest extends AnyFunSuite with Matchers:
  val store = Store()
  val service = Service(store)
  val dispatcher = Dispatcher(service)
  var account = Account(email = "test@test.com")

  test("register") {
    val command = Register(email = "test@test.com")
    dispatcher.dispatch(command) match
      case Registered(account) =>
        account.isActivated shouldBe true
        this.account = account
      case _ => fail()
  }

  test("login") {
    val command = Login(account.email, account.pin)
    dispatcher.dispatch(command) match
      case LoggedIn(account) =>
        this.account shouldBe account
        this.account = account
      case _ => fail()
  }

  test("deactivate") {
    val command = Deactivate(account.license)
    dispatcher.dispatch(command) match
      case Deactivated(account) =>
        account.deactivated > 0 shouldBe true
        account.activated == 0 shouldBe true
        this.account = account
      case _ => fail()
  }

  test("reactivate") {
    val command = Reactivate(account.license)
    dispatcher.dispatch(command) match
      case Reactivated(account) =>
        account.activated > 0 shouldBe true
        account.deactivated == 0 shouldBe true
        this.account = account
      case _ => fail()
  }