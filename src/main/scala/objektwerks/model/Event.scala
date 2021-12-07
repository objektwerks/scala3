package objektwerks.model

sealed trait Event

final case class Registering() extends Event
final case class LoggedIn(account: Account) extends Event

final case class Deactivated(account: Account) extends Event
final case class Reactivated(account: Account) extends Event

final case class Listed(entities: Seq[Entity]) extends Event
final case class Added(entity: Entity) extends Event
final case class Updated() extends Event

final case class Fault(dateOf: Int,
                       timeOf: Int,
                       nanoOf: Int,
                       code: Int,
                       cause: String) extends Event

object Fault {
  val serviceFailure = 500

  def apply(code: Int, cause: String): Fault = Fault(
    dateOf = DateTime.currentDate,
    timeOf = DateTime.currentTime,
    nanoOf = DateTime.nano,
    code = code,
    cause = cause
  )

  def apply(cause: String): Fault = apply(serviceFailure, cause)
}