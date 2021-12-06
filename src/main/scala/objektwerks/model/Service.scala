package objektwerks.model

import scala.concurrent.Future

trait Service:
  def register(register: Register): Future[Either[Fault, Registering]]
  def login(login: Login): Future[Either[Fault, LoggedIn]]
  def deactivate(deactivate: Deactivate): Future[Either[Fault, Deactivated]]
  def reactivate(reactivate: Reactivate): Future[Either[Fault, Reactivated]]

  def listPools(): Future[Either[Fault, Listed]]
  def addPool(pool: Pool): Future[Either[Fault, Added]]
  def updatePool(pool: Pool): Future[Either[Fault, Updated]]

  def listSurfaces(poolId: Int): Future[Either[Fault, Listed]]
  def addSurface(surface: Surface): Future[Either[Fault, Added]]
  def updateSurface(surface: Surface): Future[Either[Fault, Updated]]

  def listPumps(poolId: Int): Future[Either[Fault, Listed]]
  def addPump(pump: Pump): Future[Either[Fault, Added]]
  def updatePump(pump: Pump): Future[Either[Fault, Updated]]

  def listTimers(poolId: Int): Future[Either[Fault, Listed]]
  def addTimer(timer: Timer): Future[Either[Fault, Added]]
  def updateTimer(timer: Timer): Future[Either[Fault, Updated]]

  def listTimerSettings(timerId: Int): Future[Either[Fault, Listed]]
  def addTimerSetting(timerSetting: TimerSetting): Future[Either[Fault, Added]]
  def updateTimerSetting(timerSetting: TimerSetting): Future[Either[Fault, Updated]]

  def listHeaters(poolId: Int): Future[Either[Fault, Listed]]
  def addHeater(heater: Heater): Future[Either[Fault, Added]]
  def updateHeater(heater: Heater): Future[Either[Fault, Updated]]

  def listHeaterSettings(heaterId: Int): Future[Either[Fault, Listed]]
  def addHeaterSetting(heaterSetting: HeaterSetting): Future[Either[Fault, Added]]
  def updateHeaterSetting(heaterSetting: HeaterSetting): Future[Either[Fault, Updated]]

  def listMeasurements(poolId: Int): Future[Either[Fault, Listed]]
  def addMeasurement(measurement: Measurement): Future[Either[Fault, Added]]
  def updateMeasurement(measurement: Measurement): Future[Either[Fault, Updated]]

  def listCleanings(poolId: Int): Future[Either[Fault, Listed]]
  def addCleaning(cleaning: Cleaning): Future[Either[Fault, Added]]
  def updateCleaning(cleaning: Cleaning): Future[Either[Fault, Updated]]

  def listChemicals(poolId: Int): Future[Either[Fault, Listed]]
  def addChemical(chemical: Chemical): Future[Either[Fault, Added]]
  def updateChemical(chemical: Chemical): Future[Either[Fault, Updated]]