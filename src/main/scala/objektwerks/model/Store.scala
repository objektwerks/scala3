package objektwerks.model

object Store:
  def mapStore: MapStore = MapStore()

trait Store:
  def register(email: String): Account
  def login(email: String, pin: String): Option[Account]

  def deactivate(license: String): Option[Account]
  def reactivate(license: String): Option[Account]

  def listPools(): Seq[Pool]
  def addPool(pool: Pool): Pool
  def updatePool(pool: Pool): Unit

  def listSurfaces(): Seq[Surface]
  def addSurface(surface: Surface): Surface
  def updateSurface(surface: Surface): Unit

  def listPumps(): Seq[Pump]
  def addPump(pump: Pump): Pump
  def updatePump(pump: Pump): Unit

  def listTiimers(): Seq[Timer]
  def addTimer(timer: Timer): Timer
  def updateTimer(timer: Timer): Unit

  def listTiimerSettings(): Seq[TimerSetting]
  def addTimerSetting(timerSetting: TimerSetting): TimerSetting
  def updateTimerSetting(timerSetting: TimerSetting): Unit

  def listHeaters(): Seq[Heater]
  def addHeater(heater: Heater): Heater
  def updateHeater(heater: Heater): Unit

  def listHeaterSettings(): Seq[HeaterSetting]
  def addHeaterSetting(heaterSetting: HeaterSetting): HeaterSetting
  def updateHeaterSetting(heaterSetting: HeaterSetting): Unit

  def listMeasurements(): Seq[Measurement]
  def addMeasurement(measurement: Measurement): Measurement
  def updateMeasurement(measurement: Measurement): Unit

  def listCleanings(): Seq[Cleaning]
  def addCleaning(cleaning: Cleaning): Cleaning
  def updateCleaning(cleaning: Cleaning): Unit

  def listChemicals(): Seq[Chemical]
  def addChemical(chemical: Chemical): Chemical
  def updateChemical(chemical: Chemical): Unit

  def listSupplies(): Seq[Supply]
  def addSupply(supply: Supply): Supply
  def updateSupply(supply: Supply): Unit

  def listRepairs(): Seq[Repair]
  def addRepair(repair: Repair): Repair
  def updateRepair(repair: Repair): Unit