package objektwerks.model

class Authorizor(service: Service):
  def authorize(command: Command): Event =
    case license: License => service.authorize(license.license)