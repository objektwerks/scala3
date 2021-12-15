package objektwerks.model

sealed trait License:
  val license: String

sealed trait Command

final case class Register(email: String) extends Command
final case class Login(email: String, pin: String) extends Command

final case class Deactivate(license: String) extends Command with License
final case class Reactivate(license: String) extends Command with License

final case class ListPools(license: String) extends Command with License
final case class AddPool(license: String, pool: Pool) extends Command with License
final case class UpdatePool(license: String, pool: Pool) extends Command with License

final case class ListSurfaces(license: String, poolId: Int) extends Command with License
final case class AddSurface(license: String, surface: Surface) extends Command with License
final case class UpdateSurface(license: String, surface: Surface) extends Command with License

final case class ListPumps(license: String, poolId: Int) extends Command with License
final case class AddPump(license: String, pump: Pump) extends Command with License
final case class UpdatePump(license: String, pump: Pump) extends Command with License

final case class ListTimers(license: String, poolId: Int) extends Command with License
final case class AddTimer(license: String, timer: Timer) extends Command with License
final case class UpdateTimer(license: String, timer: Timer) extends Command with License

final case class ListTimerSettings(license: String, timerId: Int) extends Command with License
final case class AddTimerSetting(license: String, timerSetting: TimerSetting) extends Command with License
final case class UpdateTimerSetting(license: String, timerSetting: TimerSetting) extends Command with License

final case class ListHeaters(license: String, poolId: Int) extends Command with License
final case class AddHeater(license: String, heater: Heater) extends Command with License
final case class UpdateHeater(license: String, heater: Heater) extends Command with License

final case class ListHeaterSettings(license: String, heaterId: Int) extends Command with License
final case class AddHeaterSetting(license: String, heaterSetting: HeaterSetting) extends Command with License
final case class UpdateHeaterSetting(license: String, heaterSetting: HeaterSetting) extends Command with License

final case class ListMeasurements(license: String, poolId: Int) extends Command with License
final case class AddMeasurement(license: String, measurement: Measurement) extends Command with License
final case class UpdateMeasurement(license: String, measurement: Measurement) extends Command with License

final case class ListCleanings(license: String, poolId: Int) extends Command with License
final case class AddCleaning(license: String, cleaning: Cleaning) extends Command with License
final case class UpdateCleaning(license: String, cleaning: Cleaning) extends Command with License

final case class ListChemicals(license: String, poolId: Int) extends Command with License
final case class AddChemical(license: String, chemical: Chemical) extends Command with License
final case class UpdateChemical(license: String, chemical: Chemical) extends Command with License

final case class ListSupplies(license: String, poolId: Int) extends Command with License
final case class AddSupply(license: String, supply: Supply) extends Command with License
final case class UpdateSupply(license: String, supply: Supply) extends Command with License

final case class ListRepairs(license: String, poolId: Int) extends Command with License
final case class AddRepair(license: String, repair: Repair) extends Command with License
final case class UpdateRepair(license: String, repair: Repair) extends Command with License