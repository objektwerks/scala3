package objektwerks.model

object Dispatcher:
  val service = Service.syncService

  def dispatch(command: Command): Event =
    command match
      case register: Register =>
        service.register(register.email).fold(throwable => Fault(throwable), account => Registered(account))
      case login: Login =>
        service.login(login.email, login.pin).fold(throwable => Fault(throwable), account => LoggedIn(account))

      case deactivate: Deactivate =>
        service.deactivate(deactivate.license).fold(throwable => Fault(throwable), account => Deactivated(account))
      case reactivate: Reactivate =>
        service.reactivate(reactivate.license).fold(throwable => Fault(throwable), account => Reactivated(account))

      case list: ListPools => service.listPools().fold(throwable => Fault(throwable), entities => Listed(entities))
      case add: AddPool => service.addPool(add.pool).fold(throwable => Fault(throwable), entity => Added(entity))
      case update: UpdatePool => service.updatePool(update.pool).fold(throwable => Fault(throwable), _ => Updated())

      case list: ListSurfaces => ???
      case add: AddSurface => ???
      case update: UpdateSurface => ???

      case list: ListPumps => ???
      case add: AddPump => ???
      case update: UpdatePump => ???

      case list: ListTimers => ???
      case add: AddTimer => ???
      case update: UpdateTimer => ???

      case list: ListTimerSettings => ???
      case add: AddTimerSetting => ???
      case update: UpdateTimerSetting => ???

      case list: ListHeaters => ???
      case add: AddHeater => ???
      case update: UpdateHeater => ???

      case list: ListHeaterSettings => ???
      case add: AddHeaterSetting => ???
      case update: UpdateHeaterSetting => ???

      case list: ListMeasurements => ???
      case add: AddMeasurement => ???
      case update: UpdateMeasurement => ???

      case list: ListCleanings => ???
      case add: AddCleaning => ???
      case update: UpdateCleaning => ???

      case list: ListChemicals => ???
      case add: AddChemical => ???
      case update: UpdateChemical => ???

      case list: ListSupplies => ???
      case add: AddSupply => ???
      case update: UpdateSupply => ???

      case list: ListRepairs => ???
      case add: AddRepair => ???
      case update: UpdateRepair => ???