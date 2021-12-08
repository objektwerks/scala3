package objektwerks.model

trait Dispatcher(service: Service):
  def dispatch(command: Command): Event