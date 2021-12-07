package objektwerks.model

import scala.collection.mutable

object Store:
  private val accounts = mutable.Map.empty[String, Account]
  private val pools = mutable.Map.empty[Int, Pool]
  private val surfaces = mutable.Map.empty[Int, Surface]
  private val pumps = mutable.Map.empty[Int, Pump]
  private val timers = mutable.Map.empty[Int, Timer]
  private val timerSettings = mutable.Map.empty[Int, TimerSetting]
  private val heaters = mutable.Map.empty[Int, Heater]
  private val heaterSettings = mutable.Map.empty[Int, HeaterSetting]
  private val measurements = mutable.Map.empty[Int, Measurement]
  private val cleanings = mutable.Map.empty[Int, Cleaning]

  def register(email: String): Account =
    val account = Account(email)
    accounts.addOne(account.license, account)
    account

  def login(email: String, pin: String): Option[Account] =
    accounts.values.find(account => account.email == email && account.pin == pin)

  def deactivate(license: String): Option[Account] =
    val account = accounts.get(license)
    if account.nonEmpty then
      Some( account.get.copy(deactivated = DateTime.currentDate, activated = 0) )
    else None

  def reactivate(license: String): Option[Account] =
    val account = accounts.get(license)
    if account.nonEmpty then
      Some( account.get.copy(activated = DateTime.currentDate, deactivated = 0) )
    else None

  def listPools(): Seq[Pool] = pools.values.to(Seq)

  def addPool(pool: Pool): Pool =
    val newPool = pool.copy(id = pools.size + 1)
    pools.addOne(newPool.id, newPool)
    newPool

  def updatePool(pool: Pool): Unit = pools.update(pool.id, pool)

  def listSurfaces(): Seq[Surface] = surfaces.values.to(Seq)

  def addSurface(surface: Surface): Surface =
    val newSurface = surface.copy(id = surfaces.size + 1)
    surfaces.addOne(newSurface.id, newSurface)
    newSurface

  def updateSurface(surface: Surface): Unit = surfaces.update(surface.id, surface)

  def listPumps(): Seq[Pump] = pumps.values.to(Seq)

  def addPump(pump: Pump): Pump =
    val newPump = pump.copy(id = pumps.size + 1)
    pumps.addOne(newPump.id, newPump)
    newPump

  def updatePump(pump: Pump): Unit = pumps.update(pump.id, pump)

  def listTiimers(): Seq[Timer] = timers.values.to(Seq)

  def addTimer(timer: Timer): Timer =
    val newTimer = timer.copy(id = timers.size + 1)
    timers.addOne(newTimer.id, newTimer)
    newTimer

  def updateTimer(timer: Timer): Unit = timers.update(timer.id, timer)

  def listTiimerSettings(): Seq[TimerSetting] = timerSettings.values.to(Seq)

  def addTimerSetting(timerSetting: TimerSetting): TimerSetting =
    val newTimerSetting = timerSetting.copy(id = timerSettings.size + 1)
    timerSettings.addOne(newTimerSetting.id, newTimerSetting)
    newTimerSetting

  def updateTimerSetting(timerSetting: TimerSetting): Unit = timerSettings.update(timerSetting.id, timerSetting)

  def listHeaters(): Seq[Heater] = heaters.values.to(Seq)

  def addHeater(heater: Heater): Heater =
    val newHeater = heater.copy(id = heaters.size + 1)
    heaters.addOne(newHeater.id, newHeater)
    newHeater

  def updateHeater(heater: Heater): Unit = heaters.update(heater.id, heater)

  def listHeaterSettings(): Seq[HeaterSetting] = heaterSettings.values.to(Seq)

  def addHeaterSetting(heaterSetting: HeaterSetting): HeaterSetting =
    val newHeaterSetting = heaterSetting.copy(id = heaterSettings.size + 1)
    heaterSettings.addOne(newHeaterSetting.id, newHeaterSetting)
    newHeaterSetting

  def updateHeaterSetting(heaterSetting: HeaterSetting): Unit = heaterSettings.update(heaterSetting.id, heaterSetting)

  def listMeasurements(): Seq[Measurement] = measurements.values.to(Seq)

  def addMeasurement(measurement: Measurement): Measurement =
    val newMeasurement = measurement.copy(id = measurements.size + 1)
    measurements.addOne(newMeasurement.id, newMeasurement)
    newMeasurement

  def updateMeasurement(measurement: Measurement): Unit = measurements.update(measurement.id, measurement)

  def listCleanings(): Seq[Cleaning] = cleanings.values.to(Seq)

  def addCleaning(cleaning: Cleaning): Cleaning =
    val newCleaning = cleaning.copy(id = cleanings.size + 1)
    cleanings.addOne(newCleaning.id, newCleaning)
    newCleaning

  def updateCleaning(cleaning: Cleaning): Unit = cleanings.update(cleaning.id, cleaning)