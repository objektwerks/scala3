package objektwerks.model

import scala.concurrent.Future

trait Service:
  def register(email: String): Future[Either[Throwable, Unit]]
  def login(email: String, pin: String): Future[Either[Throwable, Account]]
  def deactivate(deactivate: Deactivate): Future[Either[Throwable, Account]]
  def reactivate(reactivate: Reactivate): Future[Either[Throwable, Account]]

  def listPools(): Future[Either[Throwable, Seq[Pool]]]
  def addPool(pool: Pool): Future[Either[Throwable, Pool]]
  def updatePool(pool: Pool): Future[Either[Throwable, Pool]]

  def listSurfaces(poolId: Int): Future[Either[Throwable, Seq[Surface]]]
  def addSurface(surface: Surface): Future[Either[Throwable, Surface]]
  def updateSurface(surface: Surface): Future[Either[Throwable, Surface]]

  def listPumps(poolId: Int): Future[Either[Throwable, Seq[Pump]]]
  def addPump(pump: Pump): Future[Either[Throwable, Pump]]
  def updatePump(pump: Pump): Future[Either[Throwable, Pump]]

  def listTimers(poolId: Int): Future[Either[Throwable, Seq[Timer]]]
  def addTimer(timer: Timer): Future[Either[Throwable, Timer]]
  def updateTimer(timer: Timer): Future[Either[Throwable, Timer]]

  def listTimerSettings(timerId: Int): Future[Either[Throwable, Seq[TimerSetting]]]
  def addTimerSetting(timerSetting: TimerSetting): Future[Either[Throwable, TimerSetting]]
  def updateTimerSetting(timerSetting: TimerSetting): Future[Either[Throwable, TimerSetting]]

  def listHeaters(poolId: Int): Future[Either[Throwable, Seq[Heater]]]
  def addHeater(heater: Heater): Future[Either[Throwable, Heater]]
  def updateHeater(heater: Heater): Future[Either[Throwable, Heater]]

  def listHeaterSettings(heaterId: Int): Future[Either[Throwable, Seq[HeaterSetting]]]
  def addHeaterSetting(heaterSetting: HeaterSetting): Future[Either[Throwable, HeaterSetting]]
  def updateHeaterSetting(heaterSetting: HeaterSetting): Future[Either[Throwable, HeaterSetting]]

  def listMeasurements(poolId: Int): Future[Either[Throwable, Seq[Measurement]]]
  def addMeasurement(measurement: Measurement): Future[Either[Throwable, Measurement]]
  def updateMeasurement(measurement: Measurement): Future[Either[Throwable, Measurement]]

  def listCleanings(poolId: Int): Future[Either[Throwable, Seq[Cleaning]]]
  def addCleaning(cleaning: Cleaning): Future[Either[Throwable, Cleaning]]
  def updateCleaning(cleaning: Cleaning): Future[Either[Throwable, Cleaning]]

  def listChemicals(poolId: Int): Future[Either[Throwable, Seq[Chemical]]]
  def addChemical(chemical: Chemical): Future[Either[Throwable, Chemical]]
  def updateChemical(chemical: Chemical): Future[Either[Throwable, Chemical]]

  def listSupplies(poolId: Int): Future[Either[Throwable, Seq[Supply]]]
  def addSupply(supply: Supply): Future[Either[Throwable, Supply]]
  def updateSupply(supply: Supply): Future[Either[Throwable, Supply]]

  def listRepairs(poolId: Int): Future[Either[Throwable, Seq[Repair]]]
  def addRepair(repair: Repair): Future[Either[Throwable, Repair]]
  def updateRepair(repair: Repair): Future[Either[Throwable, Repair]]