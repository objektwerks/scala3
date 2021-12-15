package objektwerks.model

import Validators._

class Dispatcher(authorizer: Authorizer, handler: Handler):
  def dispatch(command: Command): Event =
    authorizer.authorize(command) match
      case unauthorized: Unauthorized => unauthorized
      case _ => handler.handle(command)