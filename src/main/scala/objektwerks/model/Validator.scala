package objektwerks.model

object Validator:
  private val temp = 0 to 100
  private val totalHardness = 1 to 1000
  private val totalChlorine = 0 to 10
  private val totalBromine = 0 to 20
  private val freeChlorine = 0 to 10
  private val totalAlkalinity = 0 to 240
  private val cyanuricAcid = 0 to 300

  extension (value: String)
    def isLicense: Boolean = if value.nonEmpty then value.length == 36 else false
    def isEmail: Boolean = value.nonEmpty && value.length >=3 && value.contains("@")
    def isPin: Boolean = value.length == 9

  extension (register: Register)
    def isValid: Boolean = register.email.isEmail

  extension (login: Login)
    def isValid: Boolean = login.email.isEmail && login.pin.isPin

  extension (deactivate: Deactivate)
    def isValid: Boolean = deactivate.license.isLicense

  extension (reactivate: Reactivate)
    def isValid: Boolean = reactivate.license.isLicense

  extension (account: Account)
    def isActivated: Boolean =
      account.license.isLicense &&
      account.email.isEmail &&
      account.pin.isPin &&
      account.activated > 0 &&
      account.deactivated == 0
    def isDeactivated: Boolean =
      account.license.isLicense &&
      account.email.isEmail &&
      account.pin.isPin &&
      account.activated > 0 &&
      account.deactivated > 0

  extension (pool: Pool)
    def isValid: Boolean =
      pool.id >= 0 &&
      pool.license.isLicense &&
      pool.name.nonEmpty &&
      pool.built > 0 &&
      pool.volume >= 1000

  extension (surface: Surface)
    def isValid: Boolean =
      surface.id >= 0 &&
      surface.poolId > 0 &&
      surface.installed > 0 &&
      surface.kind.nonEmpty

  extension (pump: Pump)
    def isValid: Boolean =
      pump.id >= 0 &&
      pump.poolId > 0 &&
      pump.installed > 0 &&
      pump.model.nonEmpty

  extension (timer: Timer)
    def isValid: Boolean =
      timer.id >= 0 &&
      timer.poolId > 0 &&
      timer.installed > 0 &&
      timer.model.nonEmpty

  extension (timerSetting: TimerSetting)
    def isValid: Boolean =
      timerSetting.id >= 0 &&
      timerSetting.timerId > 0 &&
      timerSetting.created > 0 &&
      timerSetting.timeOn > 0 &&
      timerSetting.timeOff > 0 &&
      timerSetting.timeOff > timerSetting.timeOn

  extension (heater: Heater)
    def isValid: Boolean =
      heater.id >= 0 &&
      heater.poolId > 0 &&
      heater.installed > 0 &&
      heater.model.nonEmpty

  extension (heaterSetting: HeaterSetting)
    def isValid: Boolean =
      heaterSetting.id >= 0 &&
      heaterSetting.heaterId > 0 &&
      heaterSetting.temp > 0 &&
      heaterSetting.dateOn > 0 &&
      heaterSetting.dateOff >= 0

  extension (measurement: Measurement)
    def isValid: Boolean =
      measurement.id >= 0 &&
      measurement.poolId > 0 &&
      measurement.measured > 0 &&
      temp.contains(measurement.temp) &&
      totalHardness.contains(measurement.totalHardness) &&
      totalChlorine.contains(measurement.totalChlorine) &&
      totalBromine.contains(measurement.totalBromine) &&
      freeChlorine.contains(measurement.freeChlorine) &&
      (measurement.ph >= 6.2 && measurement.ph <= 8.4) &&
      totalAlkalinity.contains(measurement.totalAlkalinity) &&
      cyanuricAcid.contains(measurement.cyanuricAcid)

  extension (cleaning: Cleaning)
    def isValid: Boolean =
      cleaning.id >= 0 &&
      cleaning.poolId > 0 &&
      cleaning.cleaned > 0

  extension (chemical: Chemical)
    def isValid: Boolean =
      chemical.id >= 0 &&
      chemical.poolId > 0 &&
      chemical.added > 0 &&
      chemical.chemical.nonEmpty &&
      chemical.amount > 0.00 &&
      chemical.unit.nonEmpty

  extension (supply: Supply)
    def isValid: Boolean =
      supply.id >= 0 &&
      supply.poolId > 0 &&
      supply.purchased > 0 &&
      supply.cost > 0.00 &&
      supply.item.nonEmpty &&
      supply.amount > 0.00 &&
      supply.unit.nonEmpty

  extension (repair: Repair)
    def isValid: Boolean =
      repair.id >= 0 &&
      repair.poolId > 0 &&
      repair.repaired > 0 &&
      repair.cost > 0.00 &&
      repair.repair.nonEmpty