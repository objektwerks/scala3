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

    var surface = Surface(poolId = pool.id, installed = 1, kind = "concrete")
    surface = testAddSurface(pool, surface)
    testListSurfaces(pool)
    surface = testUpdateSurface(pool, surface)

    var pump = Pump(poolId = pool.id, installed = 1, model = "hayward")
    pump = testAddPump(pool, pump)
    testListPumps(pool)
    pump = testUpdatePump(pool, pump)

    var timer = Timer(poolId = pool.id, installed = 1, model = "intermatic")
    timer = testAddTimer(pool, timer)
    testListTimers(pool)
    timer = testUpdateTimer(pool, timer)

    var timerSetting = TimerSetting(timerId = timer.id, created = 1, timeOn = 1, timeOff = 2)
    timerSetting = testAddTimerSetting(pool, timerSetting)
    testListTimerSettings(pool, timer)
    timerSetting = testUpdateTimerSetting(pool, timerSetting)

    var heater = Heater(poolId = pool.id, installed = 1, model = "hayward")
    heater = testAddHeater(pool, heater)
    testListHeaters(pool)
    heater = testUpdateHeater(pool, heater)
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

  def testAddPump(pool: Pool, pump: Pump): Pump =
    val add = AddPump(pool.license, pump)
    dispatcher.dispatch(add) match
      case Added(pump: Pump) =>
        pump.id > 0 shouldBe true
        pump
      case _ => fail()

  def testListPumps(pool: Pool): Unit =
    val list = ListPumps(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(pumps) => pumps.size shouldBe 1
      case _ => fail()

  def testUpdatePump(pool: Pool, pump: Pump): Pump =
    val updatedPump = pump.copy(model = "pentair")
    val update = UpdatePump(pool.license, updatedPump)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedPump

  def testAddTimer(pool: Pool, timer: Timer): Timer =
    val add = AddTimer(pool.license, timer)
    dispatcher.dispatch(add) match
      case Added(timer: Timer) =>
        timer.id > 0 shouldBe true
        timer
      case _ => fail()

  def testListTimers(pool: Pool): Unit =
    val list = ListTimers(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(timers) => timers.size shouldBe 1
      case _ => fail()

  def testUpdateTimer(pool: Pool, timer: Timer): Timer =
    val updatedTimer = timer.copy(model = "smartpool")
    val update = UpdateTimer(pool.license, updatedTimer)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedTimer

  def testAddTimerSetting(pool: Pool, timerSetting: TimerSetting): TimerSetting =
    val add = AddTimerSetting(pool.license, timerSetting)
    dispatcher.dispatch(add) match
      case Added(timerSetting: TimerSetting) =>
        timerSetting.id > 0 shouldBe true
        timerSetting
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListTimerSettings(pool: Pool, timer: Timer): Unit =
    val list = ListTimerSettings(pool.license, timer.id)
    dispatcher.dispatch(list) match
      case Listed(timerSettings) => timerSettings.size shouldBe 1
      case _ => fail()

  def testUpdateTimerSetting(pool: Pool, timerSetting: TimerSetting): TimerSetting =
    val updatedTimerSetting = timerSetting.copy(timeOff = 3)
    val update = UpdateTimerSetting(pool.license, updatedTimerSetting)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedTimerSetting

  def testAddHeater(pool: Pool, heater: Heater): Heater =
    val add = AddHeater(pool.license, heater)
    dispatcher.dispatch(add) match
      case Added(heater: Heater) =>
        heater.id > 0 shouldBe true
        heater
      case _ => fail()

  def testListHeaters(pool: Pool): Unit =
    val list = ListHeaters(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(heaters) => heaters.size shouldBe 1
      case _ => fail()

  def testUpdateHeater(pool: Pool, heater: Heater): Heater =
    val updatedHeater = heater.copy(model = "pentair")
    val update = UpdateHeater(pool.license, updatedHeater)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedHeater