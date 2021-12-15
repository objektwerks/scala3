package objektwerks.model

import Validators._

class Dispatcher(authorizor: Authorizor, handler: Handler):
  def dispatch(command: Command): Event =
    authorizor.authorize(command) match
      case unauthorized: Unauthorized => unauthorized
      case _ => handler.handle(command)