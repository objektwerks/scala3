package objektwerks.model

object Dispatcher:
  val service = Service.syncService

  def dispatch(command: Command): Event =
    command match
      case register: Register =>
        service.register(register.email) match
          case Right(account) => Registered(account)
          case Left(throwable) => Fault(throwable)

      case login: Login =>
        service.login(login.email, login.pin) match
          case Right(account) => LoggedIn(account)
          case Left(throwable) => Fault(throwable)

      case deactivate: Deactivate =>
        service.deactivate(deactivate.license) match
          case Right(account) => Deactivated(account)
          case Left(throwable) => Fault(throwable)

      case reactivate: Reactivate =>
        service.reactivate(reactivate.license) match
          case Right(account) => Reactivated(account)
          case Left(throwable) => Fault(throwable)

      case list: ListPools => ???
      case add: AddPool => ???
      case update: UpdatePool => ???

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