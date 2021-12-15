package objektwerks.model

class Authorizor(service: Service):
  def authorize(command: Command): Event =
    command match
      case license: License => service.authorize(license.license)
      case _: NoLicense => Authorized("")