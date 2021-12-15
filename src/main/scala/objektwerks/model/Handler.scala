package objektwerks.model

import Validators._

class Handler(service: Service):
  def handle(command: Command): Event =
    command match
      case register: Register =>
        if register.isValid then
          service.register(register.email).fold(throwable => Fault(throwable), account => Registered(account))
        else Fault(s"Invalid register: $register")

      case login: Login =>
        if login.isValid then
          service.login(login.email, login.pin).fold(throwable => Fault(throwable), account => LoggedIn(account))
        else Fault(s"Invalid login: $login")

      case deactivate: Deactivate =>
        if deactivate.isValid then
          service.deactivate(deactivate.license).fold(throwable => Fault(throwable), account => Deactivated(account))
        else Fault(s"Invalid deactivate: $deactivate")

      case reactivate: Reactivate =>
        service.reactivate(reactivate.license).fold(throwable => Fault(throwable), account => Reactivated(account))

      case list: ListPools =>
        service.listPools().fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddPool =>
        if add.pool.isValid then
          service.addPool(add.pool).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid pool: ${add.pool}")

      case update: UpdatePool =>
        if update.pool.isValid then
          service.updatePool(update.pool).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid pool: ${update.pool}")

      case list: ListSurfaces =>
        service.listSurfaces(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddSurface =>
        if add.surface.isValid then
          service.addSurface(add.surface).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid surface: ${add.surface}")

      case update: UpdateSurface =>
        if update.surface.isValid then
          service.updateSurface(update.surface).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid surface: ${update.surface}")

      case list: ListPumps =>
        service.listPumps(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddPump =>
        if add.pump.isValid then
          service.addPump(add.pump).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid pump: ${add.pump}")

      case update: UpdatePump =>
        if update.pump.isValid then
          service.updatePump(update.pump).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid pump: ${update.pump}")

      case list: ListTimers =>
        service.listTimers(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddTimer =>
        if add.timer.isValid then
          service.addTimer(add.timer).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid timer: ${add.timer}")

      case update: UpdateTimer =>
        if update.timer.isValid then
          service.updateTimer(update.timer).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid timer: ${update.timer}")

      case list: ListTimerSettings =>
        service.listTimerSettings(list.timerId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddTimerSetting =>
        if add.timerSetting.isValid then
          service.addTimerSetting(add.timerSetting).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid timer setting: ${add.timerSetting}")

      case update: UpdateTimerSetting =>
        if update.timerSetting.isValid then
          service.updateTimerSetting(update.timerSetting).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid timer setting: ${update.timerSetting}")

      case list: ListHeaters =>
        service.listHeaters(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddHeater =>
        if add.heater.isValid then
          service.addHeater(add.heater).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid heater: ${add.heater}")

      case update: UpdateHeater =>
        if update.heater.isValid then
          service.updateHeater(update.heater).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid heater: ${update.heater}")

      case list: ListHeaterSettings =>
        service.listHeaterSettings(list.heaterId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddHeaterSetting =>
        if add.heaterSetting.isValid then
          service.addHeaterSetting(add.heaterSetting).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid heater setting: ${add.heaterSetting}")

      case update: UpdateHeaterSetting =>
        if update.heaterSetting.isValid then
          service.updateHeaterSetting(update.heaterSetting).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid heater setting: ${update.heaterSetting}")

      case list: ListMeasurements =>
        service.listMeasurements(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddMeasurement =>
        if add.measurement.isValid then
          service.addMeasurement(add.measurement).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid measurement: ${add.measurement}")

      case update: UpdateMeasurement =>
        if update.measurement.isValid then
          service.updateMeasurement(update.measurement).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid measurement: ${update.measurement}")

      case list: ListCleanings =>
        service.listCleanings(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddCleaning =>
        if add.cleaning.isValid then
          service.addCleaning(add.cleaning).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid cleaning: ${add.cleaning}")

      case update: UpdateCleaning =>
        if update.cleaning.isValid then
          service.updateCleaning(update.cleaning).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid cleaning: ${update.cleaning}")

      case list: ListChemicals =>
        service.listChemicals(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddChemical =>
        if add.chemical.isValid then
          service.addChemical(add.chemical).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid chemical: ${add.chemical}")

      case update: UpdateChemical =>
        if update.chemical.isValid then
          service.updateChemical(update.chemical).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid chemical: ${update.chemical}")

      case list: ListSupplies =>
        service.listSupplies(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddSupply =>
        if add.supply.isValid then
          service.addSupply(add.supply).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid supply: ${add.supply}")

      case update: UpdateSupply =>
        if update.supply.isValid then
          service.updateSupply(update.supply).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid supply: ${update.supply}")

      case list: ListRepairs =>
        service.listRepairs(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))

      case add: AddRepair =>
        if add.repair.isValid then
          service.addRepair(add.repair).fold(throwable => Fault(throwable), entity => Added(entity))
        else Fault(s"Invalid repair: ${add.repair}")

      case update: UpdateRepair =>
        if update.repair.isValid then
          service.updateRepair(update.repair).fold(throwable => Fault(throwable), _ => Updated())
        else Fault(s"Invalid repair: ${update.repair}")