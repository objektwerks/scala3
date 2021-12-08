package objektwerks.model

import Validator._

class SyncDispatcher(service: Service) extends Dispatcher(service):
  def dispatch(command: Command): Event =
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
        if service.isAuthorized(deactivate.license) then
          service.deactivate(deactivate.license).fold(throwable => Fault(throwable), account => Deactivated(account))
        else Fault(s"Invalid license: ${deactivate.license}")

      case reactivate: Reactivate =>
        if service.isAuthorized(reactivate.license) then
          service.reactivate(reactivate.license).fold(throwable => Fault(throwable), account => Reactivated(account))
        else Fault(s"Invalid license: ${reactivate.license}")

      case list: ListPools =>
        if service.isAuthorized(list.license) then
          service.listPools().fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddPool =>
        if service.isAuthorized(add.license) then
          if add.pool.isValid then
            service.addPool(add.pool).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid pool: ${add.pool}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdatePool =>
        if service.isAuthorized(update.license) then
          if update.pool.isValid then
            service.updatePool(update.pool).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid pool: ${update.pool}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListSurfaces =>
        if service.isAuthorized(list.license) then
          service.listSurfaces(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddSurface =>
        if service.isAuthorized(add.license) then
          if add.surface.isValid then
            service.addSurface(add.surface).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid surface: ${add.surface}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateSurface =>
        if service.isAuthorized(update.license) then
          if update.surface.isValid then
            service.updateSurface(update.surface).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid surface: ${update.surface}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListPumps =>
        if service.isAuthorized(list.license) then
          service.listPumps(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddPump =>
        if service.isAuthorized(add.license) then
          if add.pump.isValid then
            service.addPump(add.pump).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid pump: ${add.pump}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdatePump =>
        if service.isAuthorized(update.license) then
          if update.pump.isValid then
            service.updatePump(update.pump).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid pump: ${update.pump}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListTimers =>
        if service.isAuthorized(list.license) then
          service.listTimers(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddTimer =>
        if service.isAuthorized(add.license) then
          if add.timer.isValid then
            service.addTimer(add.timer).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid timer: ${add.timer}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateTimer =>
        if service.isAuthorized(update.license) then
          if update.timer.isValid then
            service.updateTimer(update.timer).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid timer: ${update.timer}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListTimerSettings =>
        if service.isAuthorized(list.license) then
          service.listTimerSettings(list.timerId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddTimerSetting =>
        if service.isAuthorized(add.license) then
          if add.timerSetting.isValid then
            service.addTimerSetting(add.timerSetting).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid timer setting: ${add.timerSetting}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateTimerSetting =>
        if service.isAuthorized(update.license) then
          if update.timerSetting.isValid then
            service.updateTimerSetting(update.timerSetting).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid timer setting: ${update.timerSetting}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListHeaters =>
        if service.isAuthorized(list.license) then
          service.listHeaters(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddHeater =>
        if service.isAuthorized(add.license) then
          if add.heater.isValid then
            service.addHeater(add.heater).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid heater: ${add.heater}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateHeater =>
        if service.isAuthorized(update.license) then
          if update.heater.isValid then
            service.updateHeater(update.heater).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid heater: ${update.heater}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListHeaterSettings =>
        if service.isAuthorized(list.license) then
          service.listHeaterSettings(list.heaterId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddHeaterSetting =>
        if service.isAuthorized(add.license) then
          if add.heaterSetting.isValid then
            service.addHeaterSetting(add.heaterSetting).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid heater setting: ${add.heaterSetting}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateHeaterSetting =>
        if service.isAuthorized(update.license) then
          if update.heaterSetting.isValid then
            service.updateHeaterSetting(update.heaterSetting).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid heater setting: ${update.heaterSetting}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListMeasurements =>
        if service.isAuthorized(list.license) then
          service.listMeasurements(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddMeasurement =>
        if service.isAuthorized(add.license) then
          if add.measurement.isValid then
            service.addMeasurement(add.measurement).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid measurement: ${add.measurement}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateMeasurement =>
        if service.isAuthorized(update.license) then
          if update.measurement.isValid then
            service.updateMeasurement(update.measurement).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid measurement: ${update.measurement}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListCleanings =>
        if service.isAuthorized(list.license) then
          service.listCleanings(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddCleaning =>
        if service.isAuthorized(add.license) then
          if add.cleaning.isValid then
            service.addCleaning(add.cleaning).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid cleaning: ${add.cleaning}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateCleaning =>
        if service.isAuthorized(update.license) then
          if update.cleaning.isValid then
            service.updateCleaning(update.cleaning).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid cleaning: ${update.cleaning}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListChemicals =>
        if service.isAuthorized(list.license) then
          service.listChemicals(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddChemical =>
        if service.isAuthorized(add.license) then
          if add.chemical.isValid then
            service.addChemical(add.chemical).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid chemical: ${add.chemical}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateChemical =>
        if service.isAuthorized(update.license) then
          if update.chemical.isValid then
            service.updateChemical(update.chemical).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid chemical: ${update.chemical}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListSupplies =>
        if service.isAuthorized(list.license) then
          service.listSupplies(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddSupply =>
        if service.isAuthorized(add.license) then
          if add.supply.isValid then
            service.addSupply(add.supply).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid supply: ${add.supply}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateSupply =>
        if service.isAuthorized(update.license) then
          if update.supply.isValid then
            service.updateSupply(update.supply).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid supply: ${update.supply}")
        else Fault(s"Invalid license: ${update.license}")

      case list: ListRepairs =>
        if service.isAuthorized(list.license) then
          service.listRepairs(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
        else Fault(s"Invalid license: ${list.license}")

      case add: AddRepair =>
        if service.isAuthorized(add.license) then
          if add.repair.isValid then
            service.addRepair(add.repair).fold(throwable => Fault(throwable), entity => Added(entity))
          else Fault(s"Invalid repair: ${add.repair}")
        else Fault(s"Invalid license: ${add.license}")

      case update: UpdateRepair =>
        if service.isAuthorized(update.license) then
          if update.repair.isValid then
            service.updateRepair(update.repair).fold(throwable => Fault(throwable), _ => Updated())
          else Fault(s"Invalid repair: ${update.repair}")
        else Fault(s"Invalid license: ${update.license}")