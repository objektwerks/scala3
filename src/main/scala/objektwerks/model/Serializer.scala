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

  given listSurfacesRW: ReadWriter[ListSurfaces] = macroRW
  given addSurfaceRW: ReadWriter[AddSurface] = macroRW
  given updateSurfaceRW: ReadWriter[UpdateSurface] = macroRW

  given listPumpsRW: ReadWriter[ListPumps] = macroRW
  given addPumpRW: ReadWriter[AddPump] = macroRW
  given updatePumpRW: ReadWriter[UpdatePump] = macroRW

  given listTimersRW: ReadWriter[ListTimers] = macroRW
  given addTimerRW: ReadWriter[AddTimer] = macroRW
  given updateTimerRW: ReadWriter[UpdateTimer] = macroRW

  given listTimerSettingsRW: ReadWriter[ListTimerSettings] = macroRW
  given addTimerSettingRW: ReadWriter[AddTimerSetting] = macroRW
  given updateTimerSettingRW: ReadWriter[UpdateTimerSetting] = macroRW

  given listHeatersRW: ReadWriter[ListHeaters] = macroRW
  given addHeaterRW: ReadWriter[AddHeater] = macroRW
  given updateHeaterRW: ReadWriter[UpdateHeater] = macroRW

  given listHeaterSettingsRW: ReadWriter[ListHeaterSettings] = macroRW
  given addHeaterSettingRW: ReadWriter[AddHeaterSetting] = macroRW
  given updateHeaterSettingRW: ReadWriter[UpdateHeaterSetting] = macroRW

  given listMeasurementsRW: ReadWriter[ListMeasurements] = macroRW
  given addMeasurementRW: ReadWriter[AddMeasurement] = macroRW
  given updateMeasurementRW: ReadWriter[UpdateMeasurement] = macroRW

  given listCleaningsRW: ReadWriter[ListCleanings] = macroRW
  given addCleaningRW: ReadWriter[AddCleaning] = macroRW
  given updateCleaningRW: ReadWriter[UpdateCleaning] = macroRW

  given listChemicalsRW: ReadWriter[ListChemicals] = macroRW
  given addChemicalRW: ReadWriter[AddChemical] = macroRW
  given updateChemicalRW: ReadWriter[UpdateChemical] = macroRW

  given listSuppliesRW: ReadWriter[ListSupplies] = macroRW
  given addSupplyRW: ReadWriter[AddSupply] = macroRW
  given updateSupplyRW: ReadWriter[UpdateSupply] = macroRW

  given commandRW: ReadWriter[Command] = ReadWriter.merge(
    registerRW, loginRW, deactivateRW, reactivateRW, listPoolsRW, addPoolRW, updatePoolRW,
    listSurfacesRW, addSurfaceRW, updateSurfaceRW, listPumpsRW, addPumpRW, updatePumpRW,
    listTimersRW, addTimerRW, updateTimerRW, listTimerSettingsRW, addTimerSettingRW, updateTimerSettingRW,
    listHeatersRW, addHeaterRW, updateHeaterRW, listHeaterSettingsRW, addHeaterSettingRW, updateHeaterSettingRW,
    listMeasurementsRW, addMeasurementRW, updateMeasurementRW, listCleaningsRW, addCleaningRW, updateCleaningRW,
    listChemicalsRW, addChemicalRW, updateChemicalRW, listSuppliesRW, addSupplyRW, updateSupplyRW
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