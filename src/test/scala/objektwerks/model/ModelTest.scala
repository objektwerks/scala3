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
        account.isDeactivated shouldBe true
        this.account = account
      case _ => fail()
  }

  test("reactivate") {
    val command = Reactivate(account.license)
    dispatcher.dispatch(command) match
      case Reactivated(account) =>
        account.isActivated shouldBe true
        this.account = account
      case _ => fail()
  }

  test("pool") {
    val pool = Pool(license = account.license, name = "test", built = DateTime.localDateToInt(2001, 10, 15))

    val add = AddPool(account.license, pool)
    dispatcher.dispatch(add) match
      case Added(pool: Pool) => pool.id > 0 shouldBe true
      case _ => fail()

    val list = ListPools(account.license)
    dispatcher.dispatch(list) match
      case Listed(pools) => pools.size shouldBe 1
      case _ => fail()

    val updatedPool = pool.copy(volume = 10000)
    val update = UpdatePool(account.license, updatedPool)
    dispatcher.dispatch(update) shouldBe Updated()
  }