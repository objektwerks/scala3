package objektwerks.model

trait Service:
  def register(email: String): Either[Throwable, Account]
  def login(email: String, pin: String): Either[Throwable, Account]
  
  def deactivate(license: String): Either[Throwable, Account]
  def reactivate(license: String): Either[Throwable, Account]

  def listPools(): Either[Throwable, Seq[Pool]]
  def addPool(pool: Pool): Either[Throwable, Pool]
  def updatePool(pool: Pool): Either[Throwable, Pool]

  def listSurfaces(poolId: Int): Either[Throwable, Seq[Surface]]
  def addSurface(surface: Surface): Either[Throwable, Surface]
  def updateSurface(surface: Surface): Either[Throwable, Surface]

  def listPumps(poolId: Int): Either[Throwable, Seq[Pump]]
  def addPump(pump: Pump): Either[Throwable, Pump]
  def updatePump(pump: Pump): Either[Throwable, Pump]

  def listTimers(poolId: Int): Either[Throwable, Seq[Timer]]
  def addTimer(timer: Timer): Either[Throwable, Timer]
  def updateTimer(timer: Timer): Either[Throwable, Timer]

  def listTimerSettings(timerId: Int): Either[Throwable, Seq[TimerSetting]]
  def addTimerSetting(timerSetting: TimerSetting): Either[Throwable, TimerSetting]
  def updateTimerSetting(timerSetting: TimerSetting): Either[Throwable, TimerSetting]

  def listHeaters(poolId: Int): Either[Throwable, Seq[Heater]]
  def addHeater(heater: Heater): Either[Throwable, Heater]
  def updateHeater(heater: Heater): Either[Throwable, Heater]

  def listHeaterSettings(heaterId: Int): Either[Throwable, Seq[HeaterSetting]]
  def addHeaterSetting(heaterSetting: HeaterSetting): Either[Throwable, HeaterSetting]
  def updateHeaterSetting(heaterSetting: HeaterSetting): Either[Throwable, HeaterSetting]

  def listMeasurements(poolId: Int): Either[Throwable, Seq[Measurement]]
  def addMeasurement(measurement: Measurement): Either[Throwable, Measurement]
  def updateMeasurement(measurement: Measurement): Either[Throwable, Measurement]

  def listCleanings(poolId: Int): Either[Throwable, Seq[Cleaning]]
  def addCleaning(cleaning: Cleaning): Either[Throwable, Cleaning]
  def updateCleaning(cleaning: Cleaning): Either[Throwable, Cleaning]

  def listChemicals(poolId: Int): Either[Throwable, Seq[Chemical]]
  def addChemical(chemical: Chemical): Either[Throwable, Chemical]
  def updateChemical(chemical: Chemical): Either[Throwable, Chemical]

  def listSupplies(poolId: Int): Either[Throwable, Seq[Supply]]
  def addSupply(supply: Supply): Either[Throwable, Supply]
  def updateSupply(supply: Supply): Either[Throwable, Supply]

  def listRepairs(poolId: Int): Either[Throwable, Seq[Repair]]
  def addRepair(repair: Repair): Either[Throwable, Repair]
  def updateRepair(repair: Repair): Either[Throwable, Repair]