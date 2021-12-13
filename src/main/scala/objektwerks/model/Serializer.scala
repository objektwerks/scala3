package objektwerks.model

object Serializer:
  import upickle.default._

  implicit val faultRW: ReadWriter[Fault] = macroRW

  implicit val registerRW: ReadWriter[Register] = macroRW
  implicit val loginRW: ReadWriter[Login] = macroRW
  implicit val deactivateRW: ReadWriter[Deactivate] = macroRW
  implicit val reactivateRW: ReadWriter[Reactivate] = macroRW

  implicit val commandRW: ReadWriter[Command] = ReadWriter.merge(
    registerRW, loginRW, deactivateRW, reactivateRW
  )

  implicit val registeringRW: ReadWriter[Registered] = macroRW
  implicit val loggedInRW: ReadWriter[LoggedIn] = macroRW
  implicit val deactivatedRW: ReadWriter[Deactivated] = macroRW
  implicit val reactivatedRW: ReadWriter[Reactivated] = macroRW

  implicit val eventRW: ReadWriter[Event] = ReadWriter.merge(
    registeringRW, loggedInRW, deactivatedRW, reactivatedRW
  )

  implicit val accountRW: ReadWriter[Account] = macroRW
  implicit val poolRW: ReadWriter[Pool] = macroRW
  implicit val surfaceRW: ReadWriter[Surface] = macroRW
  implicit val pumpRW: ReadWriter[Pump] = macroRW
  implicit val timerRW: ReadWriter[Timer] = macroRW
  implicit val timerSettingRW: ReadWriter[TimerSetting] = macroRW
  implicit val heaterRW: ReadWriter[Heater] = macroRW
  implicit val heaterSettingRW: ReadWriter[HeaterSetting] = macroRW
  implicit val measurementRW: ReadWriter[Measurement] = macroRW
  implicit val cleaningRW: ReadWriter[Cleaning] = macroRW
  implicit val chemicalRW: ReadWriter[Chemical] = macroRW
  implicit val supplyRW: ReadWriter[Supply] = macroRW
  implicit val repairRW: ReadWriter[Repair] = macroRW

  implicit val entityRW: ReadWriter[Entity] = ReadWriter.merge(
    accountRW, poolRW, surfaceRW, pumpRW, timerRW, timerSettingRW,
    heaterRW, heaterSettingRW, measurementRW, cleaningRW, chemicalRW, supplyRW, repairRW
  )