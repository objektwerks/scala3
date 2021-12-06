package objektwerks.model

import scala.concurrent.Future

trait Service:
  def register(register: Register): Future[Either[Fault, Unit]]
  def login(login: Login): Future[Either[Fault, Account]]
  def deactivate(deactivate: Deactivate): Future[Either[Fault, Account]]
  def reactivate(reactivate: Reactivate): Future[Either[Fault, Account]]

  def listPools(): Future[Either[Fault, Seq[Pool]]]
  def addPool(pool: Pool): Future[Either[Fault, Pool]]
  def updatePool(pool: Pool): Future[Either[Fault, Pool]]

  def listSurfaces(poolId: Int): Future[Either[Fault, Seq[Surface]]]
  def addSurface(surface: Surface): Future[Either[Fault, Surface]]
  def updateSurface(surface: Surface): Future[Either[Fault, Surface]]

  def listPumps(poolId: Int): Future[Either[Fault, Seq[Pump]]]
  def addPump(pump: Pump): Future[Either[Fault, Pump]]
  def updatePump(pump: Pump): Future[Either[Fault, Pump]]

  def listTimers(poolId: Int): Future[Either[Fault, Seq[Timer]]]
  def addTimer(timer: Timer): Future[Either[Fault, Timer]]
  def updateTimer(timer: Timer): Future[Either[Fault, Timer]]

  def listTimerSettings(timerId: Int): Future[Either[Fault, Seq[TimerSetting]]]
  def addTimerSetting(timerSetting: TimerSetting): Future[Either[Fault, TimerSetting]]
  def updateTimerSetting(timerSetting: TimerSetting): Future[Either[Fault, TimerSetting]]

  def listHeaters(poolId: Int): Future[Either[Fault, Seq[Heater]]]
  def addHeater(heater: Heater): Future[Either[Fault, Heater]]
  def updateHeater(heater: Heater): Future[Either[Fault, Heater]]

  def listHeaterSettings(heaterId: Int): Future[Either[Fault, Seq[HeaterSetting]]]
  def addHeaterSetting(heaterSetting: HeaterSetting): Future[Either[Fault, HeaterSetting]]
  def updateHeaterSetting(heaterSetting: HeaterSetting): Future[Either[Fault, HeaterSetting]]

  def listMeasurements(poolId: Int): Future[Either[Fault, Seq[Measurement]]]
  def addMeasurement(measurement: Measurement): Future[Either[Fault, Measurement]]
  def updateMeasurement(measurement: Measurement): Future[Either[Fault, Measurement]]

  def listCleanings(poolId: Int): Future[Either[Fault, Seq[Cleaning]]]
  def addCleaning(cleaning: Cleaning): Future[Either[Fault, Cleaning]]
  def updateCleaning(cleaning: Cleaning): Future[Either[Fault, Cleaning]]

  def listChemicals(poolId: Int): Future[Either[Fault, Seq[Chemical]]]
  def addChemical(chemical: Chemical): Future[Either[Fault, Chemical]]
  def updateChemical(chemical: Chemical): Future[Either[Fault, Chemical]]

  def listSupplies(poolId: Int): Future[Either[Fault, Listed]]
  def addSupply(supply: Supply): Future[Either[Fault, Added]]
  def updateSupply(supply: Supply): Future[Either[Fault, Updated]]

  def listRepairs(poolId: Int): Future[Either[Fault, Listed]]
  def addRepair(repair: Repair): Future[Either[Fault, Added]]
  def updateRepair(repair: Repair): Future[Either[Fault, Updated]]