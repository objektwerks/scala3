package objektwerks.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Validator._

class ModelTest extends AnyFunSuite with Matchers:
  val store = Store()
  val service = Service(store)
  val dispatcher = Dispatcher(service)
  var account = Account(email = "test@test.com")
  var pool = Pool(license = account.license, name = "test", built = DateTime.localDateToInt(2001, 10, 15))
  var surface = Surface(poolId = pool.id, installed = DateTime.localDateToInt(2001, 10, 15), kind = "concrete")

  test("model") {
    testRegister()
    testLogin()
    testDeactivate()
    testReactivate()
    testPool()
    testSurface()
  }

  def testRegister(): Unit =
    val command = Register(email = "test@test.com")
    dispatcher.dispatch(command) match
      case Registered(account) =>
        account.isActivated shouldBe true
        this.account = account
      case _ => fail()

  def testLogin(): Unit =
    val command = Login(account.email, account.pin)
    dispatcher.dispatch(command) match
      case LoggedIn(account) =>
        this.account shouldBe account
        this.account = account
      case _ => fail()

  def testDeactivate(): Unit =
    val command = Deactivate(account.license)
    dispatcher.dispatch(command) match
      case Deactivated(account) =>
        account.isDeactivated shouldBe true
        this.account = account
      case _ => fail()

  def testReactivate(): Unit =
    val command = Reactivate(account.license)
    dispatcher.dispatch(command) match
      case Reactivated(account) =>
        account.isActivated shouldBe true
        this.account = account
      case _ => fail()

  def testPool(): Unit =
    val add = AddPool(account.license, pool)
    dispatcher.dispatch(add) match
      case Added(pool: Pool) =>
        pool.id > 0 shouldBe true
        this.pool = pool
      case _ => fail()

    val list = ListPools(account.license)
    dispatcher.dispatch(list) match
      case Listed(pools) => pools.size shouldBe 1
      case _ => fail()

    val update = UpdatePool(account.license, pool.copy(volume = 10000))
    dispatcher.dispatch(update) shouldBe Updated()

  def testSurface(): Unit =
    val add = AddSurface(account.license, surface)
    dispatcher.dispatch(add) match
      case Added(surface: Surface) =>
        surface.id > 0 shouldBe true
        this.surface = surface
      case fault: Fault => println(fault)
      case _ => fail()

    val list = ListSurfaces(account.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(surfaces) => surfaces.size shouldBe 1
      case _ => fail()

    val update = UpdateSurface(account.license, surface.copy(kind = "pebble"))
    dispatcher.dispatch(update) shouldBe Updated()