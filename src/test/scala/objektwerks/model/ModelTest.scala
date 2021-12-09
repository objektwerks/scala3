package objektwerks.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import Validator._

class ModelTest extends AnyFunSuite with Matchers:
  val store = Store()
  val service = Service(store)
  val dispatcher = Dispatcher(service)

  test("model") {
    var account = testRegister()
    testLogin(account)
    account = testDeactivate(account)
    account = testReactivate(account)

    var pool = Pool(license = account.license, name = "test", built = DateTime.localDateToInt(2001, 10, 15))
    pool = testAddPool(pool)
    testListPools(account)
    pool = testUpdatePool(pool)

    var surface = Surface(poolId = pool.id, installed = DateTime.localDateToInt(2001, 10, 15), kind = "concrete")
    surface = testAddSurface(pool, surface)
    testListSurfaces(pool)
    surface = testUpdateSurface(pool, surface)
  }

  def testRegister(): Account =
    val command = Register(email = "test@test.com")
    dispatcher.dispatch(command) match
      case Registered(account) =>
        account.isActivated shouldBe true
        account
      case _ => fail()

  def testLogin(account: Account): Unit =
    val command = Login(account.email, account.pin)
    dispatcher.dispatch(command) match
      case loggedIn: LoggedIn => account shouldBe loggedIn.account
      case _ => fail()

  def testDeactivate(account: Account): Account =
    val command = Deactivate(account.license)
    dispatcher.dispatch(command) match
      case Deactivated(account) =>
        account.isDeactivated shouldBe true
        account
      case _ => fail()

  def testReactivate(account: Account): Account =
    val command = Reactivate(account.license)
    dispatcher.dispatch(command) match
      case Reactivated(account) =>
        account.isActivated shouldBe true
        account
      case _ => fail()

  def testAddPool(pool: Pool): Pool =
    val add = AddPool(pool.license, pool)
    dispatcher.dispatch(add) match
      case Added(pool: Pool) =>
        pool.id > 0 shouldBe true
        pool
      case _ => fail()

  def testListPools(account: Account): Unit =
    val list = ListPools(account.license)
    dispatcher.dispatch(list) match
      case Listed(pools) => pools.size shouldBe 1
      case _ => fail()

  def testUpdatePool(pool: Pool): Pool =
    val updatedPool = pool.copy(volume = 10000)
    val update = UpdatePool(pool.license, updatedPool)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedPool

  def testAddSurface(pool: Pool, surface: Surface): Surface =
    val add = AddSurface(pool.license, surface)
    dispatcher.dispatch(add) match
      case Added(surface: Surface) =>
        surface.id > 0 shouldBe true
        surface
      case _ => fail()

  def testListSurfaces(pool: Pool): Unit =
    val list = ListSurfaces(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(surfaces) => surfaces.size shouldBe 1
      case _ => fail()

  def testUpdateSurface(pool: Pool, surface: Surface): Surface =
    val updatedSurface = surface.copy(kind = "pebble")
    val update = UpdateSurface(pool.license, updatedSurface)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedSurface