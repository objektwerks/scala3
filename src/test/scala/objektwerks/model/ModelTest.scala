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
    testUpdatePool(pool.copy(volume = 10000))

    var surface = Surface(poolId = pool.id, installed = 1, kind = "concrete")
    surface = testAddSurface(pool, surface)
    testListSurfaces(pool)
    testUpdateSurface(pool, surface.copy(kind = "pebble"))

    var pump = Pump(poolId = pool.id, installed = 1, model = "hayward")
    pump = testAddPump(pool, pump)
    testListPumps(pool)
    testUpdatePump(pool, pump.copy(model = "pentair"))

    var timer = Timer(poolId = pool.id, installed = 1, model = "intermatic")
    timer = testAddTimer(pool, timer)
    testListTimers(pool)
    testUpdateTimer(pool, timer.copy(model = "smartpool"))

    var timerSetting = TimerSetting(timerId = timer.id, created = 1, timeOn = 1, timeOff = 2)
    timerSetting = testAddTimerSetting(pool, timerSetting)
    testListTimerSettings(pool, timer)
    timerSetting = testUpdateTimerSetting(pool, timerSetting)

    var heater = Heater(poolId = pool.id, installed = 1, model = "hayward")
    heater = testAddHeater(pool, heater)
    testListHeaters(pool)
    heater = testUpdateHeater(pool, heater)

    var heaterSetting = HeaterSetting(heaterId = heater.id, temp = 85, dateOn = 1)
    heaterSetting = testAddHeaterSetting(pool, heaterSetting)
    testListHeaterSettings(pool, heater)
    heaterSetting = testUpdateHeaterSetting(pool, heaterSetting)

    var measurement = Measurement(poolId = pool.id, measured = 1)
    measurement = testAddMeasurement(pool, measurement)
    testListMeasurements(pool)
    measurement = testUpdateMeasurement(pool, measurement)

    var cleaning = Cleaning(poolId = pool.id, cleaned = 1)
    cleaning = testAddCleaning(pool, cleaning)
    testListCleanings(pool)
    cleaning = testUpdateCleaning(pool, cleaning)

    var chemical = Chemical(poolId = pool.id, added = 1, chemical = "chlorine", amount = 1.0, unit = "gallon")
    chemical = testAddChemical(pool, chemical)
    testListChemicals(pool)
    chemical = testUpdateChemical(pool, chemical)

    var supply = Supply(poolId = pool.id, purchased = 1, item = "chlorine", amount = 1.0, unit = "gallon", cost = 5.00)
    supply = testAddSupply(pool, supply)
    testListSupplies(pool)
    supply = testUpdateSupply(pool, supply)

    var repair = Repair(poolId = pool.id, repaired = 1, repair = "pump", cost = 100.0)
    repair = testAddRepair(pool, repair)
    testListRepairs(pool)
    repair = testUpdateRepair(pool, repair)
  }

  def testRegister(): Account =
    val command = Register(email = "test@test.com")
    dispatcher.dispatch(command) match
      case Registered(account) =>
        account.isActivated shouldBe true
        account
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testLogin(account: Account): Unit =
    val command = Login(account.email, account.pin)
    dispatcher.dispatch(command) match
      case loggedIn: LoggedIn => account shouldBe loggedIn.account
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testDeactivate(account: Account): Account =
    val command = Deactivate(account.license)
    dispatcher.dispatch(command) match
      case Deactivated(account) =>
        account.isDeactivated shouldBe true
        account
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testReactivate(account: Account): Account =
    val command = Reactivate(account.license)
    dispatcher.dispatch(command) match
      case Reactivated(account) =>
        account.isActivated shouldBe true
        account
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testAddPool(pool: Pool): Pool =
    val add = AddPool(pool.license, pool)
    dispatcher.dispatch(add) match
      case Added(pool: Pool) =>
        pool.id > 0 shouldBe true
        pool
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListPools(account: Account): Unit =
    val list = ListPools(account.license)
    dispatcher.dispatch(list) match
      case Listed(pools) => pools.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdatePool(pool: Pool): Unit =
    val update = UpdatePool(pool.license, pool)
    dispatcher.dispatch(update) shouldBe Updated()

  def testAddSurface(pool: Pool, surface: Surface): Surface =
    val add = AddSurface(pool.license, surface)
    dispatcher.dispatch(add) match
      case Added(surface: Surface) =>
        surface.id > 0 shouldBe true
        surface
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListSurfaces(pool: Pool): Unit =
    val list = ListSurfaces(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(surfaces) => surfaces.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateSurface(pool: Pool, surface: Surface): Unit =
    val update = UpdateSurface(pool.license, surface)
    dispatcher.dispatch(update) shouldBe Updated()

  def testAddPump(pool: Pool, pump: Pump): Pump =
    val add = AddPump(pool.license, pump)
    dispatcher.dispatch(add) match
      case Added(pump: Pump) =>
        pump.id > 0 shouldBe true
        pump
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListPumps(pool: Pool): Unit =
    val list = ListPumps(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(pumps) => pumps.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdatePump(pool: Pool, pump: Pump): Unit =
    val update = UpdatePump(pool.license, pump)
    dispatcher.dispatch(update) shouldBe Updated()

  def testAddTimer(pool: Pool, timer: Timer): Timer =
    val add = AddTimer(pool.license, timer)
    dispatcher.dispatch(add) match
      case Added(timer: Timer) =>
        timer.id > 0 shouldBe true
        timer
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListTimers(pool: Pool): Unit =
    val list = ListTimers(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(timers) => timers.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateTimer(pool: Pool, timer: Timer): Unit =
    val update = UpdateTimer(pool.license, timer)
    dispatcher.dispatch(update) shouldBe Updated()

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
      case fault: Fault => fail(fault.cause)
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
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListHeaters(pool: Pool): Unit =
    val list = ListHeaters(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(heaters) => heaters.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateHeater(pool: Pool, heater: Heater): Heater =
    val updatedHeater = heater.copy(model = "pentair")
    val update = UpdateHeater(pool.license, updatedHeater)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedHeater

  def testAddHeaterSetting(pool: Pool, heaterSetting: HeaterSetting): HeaterSetting =
    val add = AddHeaterSetting(pool.license, heaterSetting)
    dispatcher.dispatch(add) match
      case Added(heaterSetting: HeaterSetting) =>
        heaterSetting.id > 0 shouldBe true
        heaterSetting
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListHeaterSettings(pool: Pool, heater: Heater): Unit =
    val list = ListHeaterSettings(pool.license, heater.id)
    dispatcher.dispatch(list) match
      case Listed(heaterSettings) => heaterSettings.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateHeaterSetting(pool: Pool, heaterSetting: HeaterSetting): HeaterSetting =
    val updatedHeaterSetting = heaterSetting.copy(dateOff = 10)
    val update = UpdateHeaterSetting(pool.license, updatedHeaterSetting)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedHeaterSetting

  def testAddMeasurement(pool: Pool, measurement: Measurement): Measurement =
    val add = AddMeasurement(pool.license, measurement)
    dispatcher.dispatch(add) match
      case Added(measurement: Measurement) =>
        measurement.id > 0 shouldBe true
        measurement
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListMeasurements(pool: Pool): Unit =
    val list = ListMeasurements(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(measurements) => measurements.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateMeasurement(pool: Pool, measurement: Measurement): Measurement =
    val updatedMeasurement = measurement.copy(freeChlorine = 5)
    val update = UpdateMeasurement(pool.license, updatedMeasurement)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedMeasurement

  def testAddCleaning(pool: Pool, cleaning: Cleaning): Cleaning =
    val add = AddCleaning(pool.license, cleaning)
    dispatcher.dispatch(add) match
      case Added(cleaning: Cleaning) =>
        cleaning.id > 0 shouldBe true
        cleaning
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListCleanings(pool: Pool): Unit =
    val list = ListCleanings(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(cleanings) => cleanings.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateCleaning(pool: Pool, cleaning: Cleaning): Cleaning =
    val updatedCleaning = cleaning.copy(deck = true)
    val update = UpdateCleaning(pool.license, updatedCleaning)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedCleaning

  def testAddChemical(pool: Pool, chemical: Chemical): Chemical =
    val add = AddChemical(pool.license, chemical)
    dispatcher.dispatch(add) match
      case Added(chemical: Chemical) =>
        chemical.id > 0 shouldBe true
        chemical
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListChemicals(pool: Pool): Unit =
    val list = ListChemicals(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(chemicals) => chemicals.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateChemical(pool: Pool, chemical: Chemical): Chemical =
    val updatedChemical = chemical.copy(amount = 2.0)
    val update = UpdateChemical(pool.license, updatedChemical)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedChemical

  def testAddSupply(pool: Pool, supply: Supply): Supply =
    val add = AddSupply(pool.license, supply)
    dispatcher.dispatch(add) match
      case Added(supply: Supply) =>
        supply.id > 0 shouldBe true
        supply
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListSupplies(pool: Pool): Unit =
    val list = ListSupplies(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(supplies) => supplies.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateSupply(pool: Pool, supply: Supply): Supply =
    val updatedSupply = supply.copy(cost = 6.0)
    val update = UpdateSupply(pool.license, updatedSupply)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedSupply

  def testAddRepair(pool: Pool, repair: Repair): Repair =
    val add = AddRepair(pool.license, repair)
    dispatcher.dispatch(add) match
      case Added(repair: Repair) =>
        repair.id > 0 shouldBe true
        repair
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testListRepairs(pool: Pool): Unit =
    val list = ListRepairs(pool.license, pool.id)
    dispatcher.dispatch(list) match
      case Listed(repairs) => repairs.size shouldBe 1
      case fault: Fault => fail(fault.cause)
      case _ => fail()

  def testUpdateRepair(pool: Pool, repair: Repair): Repair =
    val updatedRepair = repair.copy(cost = 105.0)
    val update = UpdateRepair(pool.license, updatedRepair)
    dispatcher.dispatch(update) shouldBe Updated()
    updatedRepair