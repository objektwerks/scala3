package objektwerks.model

object Serializer:
  import upickle.default._

  given accountRW: ReadWriter[Account] = macroRW
  given poolRW: ReadWriter[Pool] = macroRW
  given surfaceRW: ReadWriter[Surface] = macroRW
  given pumpRW: ReadWriter[Pump] = macroRW
  given timerRW: ReadWriter[Timer] = macroRW
  given timerSettingRW: ReadWriter[TimerSetting] = macroRW
  given heaterRW: ReadWriter[Heater] = macroRW
  given heaterSettingRW: ReadWriter[HeaterSetting] = macroRW
  given measurementRW: ReadWriter[Measurement] = macroRW
  given cleaningRW: ReadWriter[Cleaning] = macroRW
  given chemicalRW: ReadWriter[Chemical] = macroRW
  given supplyRW: ReadWriter[Supply] = macroRW
  given repairRW: ReadWriter[Repair] = macroRW

  given entityRW: ReadWriter[Entity] = ReadWriter.merge(
    accountRW, poolRW, surfaceRW, pumpRW, timerRW, timerSettingRW,
    heaterRW, heaterSettingRW, measurementRW, cleaningRW, chemicalRW, supplyRW, repairRW
  )

  given registerRW: ReadWriter[Register] = macroRW
  given loginRW: ReadWriter[Login] = macroRW
  given deactivateRW: ReadWriter[Deactivate] = macroRW
  given reactivateRW: ReadWriter[Reactivate] = macroRW

  given listPoolsRW: ReadWriter[ListPools] = macroRW
  given addPoolRW: ReadWriter[AddPool] = macroRW
  given updatePoolRW: ReadWriter[UpdatePool] = macroRW

  given commandRW: ReadWriter[Command] = ReadWriter.merge(
    registerRW, loginRW, deactivateRW, reactivateRW, listPoolsRW, addPoolRW, updatePoolRW
  )

  given registeringRW: ReadWriter[Registered] = macroRW
  given loggedInRW: ReadWriter[LoggedIn] = macroRW
  given deactivatedRW: ReadWriter[Deactivated] = macroRW
  given reactivatedRW: ReadWriter[Reactivated] = macroRW
  given listedRW: ReadWriter[Listed] = macroRW
  given addedRW: ReadWriter[Added] = macroRW
  given updatedRW: ReadWriter[Updated] = macroRW
  given faultRW: ReadWriter[Fault] = macroRW

  given eventRW: ReadWriter[Event] = ReadWriter.merge(
    registeringRW, loggedInRW, deactivatedRW, reactivatedRW, listedRW, addedRW, updatedRW, faultRW
  )