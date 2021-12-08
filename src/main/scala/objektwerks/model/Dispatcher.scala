package objektwerks.model

import Validator._

class Dispatcher(service: Service):
  def dispatch(command: Command): Event =
    command match
      case register: Register =>
        if register.email.isEmail then
          service.register(register.email).fold(throwable => Fault(throwable), account => Registered(account))
        else Fault(s"Invalid email address: ${register.email}")

      case login: Login =>
        if login.email.isEmail then
          if login.pin.isPin then
            service.login(login.email, login.pin).fold(throwable => Fault(throwable), account => LoggedIn(account))
          else Fault(s"Invalid pin: ${login.pin}")
        else Fault(s"Invalid email address: ${login.email}")

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
        service.listHeaterSettings(list.heaterId).fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddHeaterSetting =>
        service.addHeaterSetting(add.heaterSetting).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdateHeaterSetting =>
        service.updateHeaterSetting(update.heaterSetting).fold(throwable => Fault(throwable), _ => Updated())

      case list: ListMeasurements =>
        service.listMeasurements(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddMeasurement =>
        service.addMeasurement(add.measurement).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdateMeasurement =>
        service.updateMeasurement(update.measurement).fold(throwable => Fault(throwable), _ => Updated())

      case list: ListCleanings =>
        service.listCleanings(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddCleaning =>
        service.addCleaning(add.cleaning).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdateCleaning =>
        service.updateCleaning(update.cleaning).fold(throwable => Fault(throwable), _ => Updated())

      case list: ListChemicals =>
        service.listChemicals(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddChemical =>
        service.addChemical(add.chemical).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdateChemical =>
        service.updateChemical(update.chemical).fold(throwable => Fault(throwable), _ => Updated())

      case list: ListSupplies =>
        service.listSupplies(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddSupply =>
        service.addSupply(add.supply).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdateSupply =>
        service.updateSupply(update.supply).fold(throwable => Fault(throwable), _ => Updated())

      case list: ListRepairs =>
        service.listRepairs(list.poolId).fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddRepair =>
        service.addRepair(add.repair).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdateRepair =>
        service.updateRepair(update.repair).fold(throwable => Fault(throwable), _ => Updated())