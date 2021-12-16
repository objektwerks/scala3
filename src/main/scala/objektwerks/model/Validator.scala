package objektwerks.model

import Validation._

class Validator(handler: Handler):
  def validate(command: Command): Event =
    val isValid = command match
      case register: Register => register.isValid
      case login: Login => login.isValid

      case deactivate: Deactivate => deactivate.isValid
      case reactivate: Reactivate => reactivate.isValid

      case list: ListPools => true
      case add: AddPool => add.pool.isValid
      case update: UpdatePool => update.pool.isValid

      case list: ListSurfaces => true
      case add: AddSurface => add.surface.isValid
      case update: UpdateSurface => update.surface.isValid

      case _ => false

    if isValid then handler.handle(command)
    else Fault(s"Invalid command: $command")