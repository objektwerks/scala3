package objektwerks.model

class SyncService extends Service:
  val store = Store.mapStore

  def register(email: String): Either[Throwable, Account] = Right( store.register(email) )

  def login(email: String, pin: String): Either[Throwable, Account] =
    store.login(email, pin) match
      case Some(account) => Right(account)
      case None => Left(IllegalArgumentException(s"Login failed for email: $email and pin: $pin"))

  def deactivate(license: String): Either[Throwable, Account] =
    store.deactivate(license) match
      case Some(account) => Right(account)
      case None => Left(IllegalArgumentException(s"License is invalid: $license"))

  def reactivate(license: String): Either[Throwable, Account] =
    store.reactivate(license) match
      case Some(account) => Right(account)
      case None => Left(IllegalArgumentException(s"License is invalid: $license"))

  def listPools(): Either[Throwable, Seq[Pool]] = Right( store.listPools() )
  def addPool(pool: Pool): Either[Throwable, Pool] = Right( store.addPool(pool) )
  def updatePool(pool: Pool): Either[Throwable, Unit] = Right( store.updatePool(pool) )

  def listSurfaces(poolId: Int): Either[Throwable, Seq[Surface]] = Right( store.listSurfaces() )
  def addSurface(surface: Surface): Either[Throwable, Surface] = Right( store.addSurface(surface) )
  def updateSurface(surface: Surface): Either[Throwable, Unit] = Right( store.updateSurface(surface) )

  def listPumps(poolId: Int): Either[Throwable, Seq[Pump]] = Right( store.listPumps() )
  def addPump(pump: Pump): Either[Throwable, Pump] = Right( store.addPump(pump) )
  def updatePump(pump: Pump): Either[Throwable, Unit] = Right( store.updatePump(pump) )

  def listTimers(poolId: Int): Either[Throwable, Seq[Timer]] = Right( store.listTiimers() )
  def addTimer(timer: Timer): Either[Throwable, Timer] = Right( store.addTimer(timer) )
  def updateTimer(timer: Timer): Either[Throwable, Unit] = Right( store.updateTimer(timer) )

  def listTimerSettings(timerId: Int): Either[Throwable, Seq[TimerSetting]] = Right( store.listTiimerSettings() )
  def addTimerSetting(timerSetting: TimerSetting): Either[Throwable, TimerSetting] = Right( store.addTimerSetting(timerSetting) )
  def updateTimerSetting(timerSetting: TimerSetting): Either[Throwable, Unit] = Right( store.updateTimerSetting(timerSetting) )

  def listHeaters(poolId: Int): Either[Throwable, Seq[Heater]] = Right( store.listHeaters() )
  def addHeater(heater: Heater): Either[Throwable, Heater] = Right( store.addHeater(heater) )
  def updateHeater(heater: Heater): Either[Throwable, Unit] = Right( store.updateHeater(heater) )

  def listHeaterSettings(heaterId: Int): Either[Throwable, Seq[HeaterSetting]] = Right( store.listTiimerSettings() )
  def addHeaterSetting(heaterSetting: HeaterSetting): Either[Throwable, HeaterSetting] = Right( store.addHeaterSetting(heaterSetting) )
  def updateHeaterSetting(heaterSetting: HeaterSetting): Either[Throwable, Unit] = Right( store.updateHeaterSetting(heaterSetting) )

  def listMeasurements(poolId: Int): Either[Throwable, Seq[Measurement]] = Right( store.listMeasurements() )
  def addMeasurement(measurement: Measurement): Either[Throwable, Measurement] = Right( store.addMeasurement(measurement) )
  def updateMeasurement(measurement: Measurement): Either[Throwable, Unit] = Right( store.updateMeasurement(measurement) )

  def listCleanings(poolId: Int): Either[Throwable, Seq[Cleaning]] = Right( store.listCleanings() )
  def addCleaning(cleaning: Cleaning): Either[Throwable, Cleaning] = Right( store.addCleaning(cleaning) )
  def updateCleaning(cleaning: Cleaning): Either[Throwable, Unit] = Right( store.updateCleaning(cleaning) )

  def listChemicals(poolId: Int): Either[Throwable, Seq[Chemical]] = ???
  def addChemical(chemical: Chemical): Either[Throwable, Chemical] = ???
  def updateChemical(chemical: Chemical): Either[Throwable, Unit] = ???

  def listSupplies(poolId: Int): Either[Throwable, Seq[Supply]] = ???
  def addSupply(supply: Supply): Either[Throwable, Supply] = ???
  def updateSupply(supply: Supply): Either[Throwable, Unit] = ???

  def listRepairs(poolId: Int): Either[Throwable, Seq[Repair]] = ???
  def addRepair(repair: Repair): Either[Throwable, Repair] = ???
  def updateRepair(repair: Repair): Either[Throwable, Unit] = ???