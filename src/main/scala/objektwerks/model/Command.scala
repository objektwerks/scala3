package objektwerks.model

sealed trait Command

final case class Register(email: String) extends Command
final case class Login(email: String, pin: String) extends Command

final case class Deactivate(license: String) extends Command
final case class Reactivate(license: String) extends Command

final case class ListPools(license: String) extends Command
final case class AddPool(license: String, pool: Pool) extends Command
final case class UpdatePool(license: String, pool: Pool) extends Command

final case class ListSurfaces(license: String, poolId: Int) extends Command
final case class AddSurface(license: String, surface: Surface) extends Command
final case class UpdateSurface(license: String, surface: Surface) extends Command

final case class ListPumps(license: String, poolId: Int) extends Command
final case class AddPump(license: String, pump: Pump) extends Command
final case class UpdatePump(license: String, pump: Pump) extends Command

final case class ListTimers(license: String, poolId: Int) extends Command
final case class AddTimer(license: String, timer: Timer) extends Command
final case class UpdateTimer(license: String, timer: Timer) extends Command

final case class ListTimerSettings(license: String, timerId: Int) extends Command
final case class AddTimerSetting(license: String, timerSetting: TimerSetting) extends Command
final case class UpdateTimerSetting(license: String, timerSetting: TimerSetting) extends Command

final case class ListHeaters(license: String, poolId: Int) extends Command
final case class AddHeater(license: String, heater: Heater) extends Command
final case class UpdateHeater(license: String, heater: Heater) extends Command

final case class ListHeaterSettings(heaterId: Int) extends Command
final case class AddHeaterSetting(heaterSetting: HeaterSetting) extends Command
final case class UpdateHeaterSetting(heaterSetting: HeaterSetting) extends Command

final case class ListMeasurements(poolId: Int) extends Command
final case class AddMeasurement(measurement: Measurement) extends Command
final case class UpdateMeasurement(measurement: Measurement) extends Command

final case class ListCleanings(poolId: Int) extends Command
final case class AddCleaning(cleaning: Cleaning) extends Command
final case class UpdateCleaning(cleaning: Cleaning) extends Command

final case class ListChemicals(poolId: Int) extends Command
final case class AddChemical(chemical: Chemical) extends Command
final case class UpdateChemical(chemical: Chemical) extends Command

final case class ListSupplies(poolId: Int) extends Command
final case class AddSupply(supply: Supply) extends Command
final case class UpdateSupply(supply: Supply) extends Command

final case class ListRepairs(poolId: Int) extends Command
final case class AddRepair(repair: Repair) extends Command
final case class UpdateRepair(repair: Repair) extends Command