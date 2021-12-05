package objektwerks

sealed trait Event

case object Registering extends Event
final case class LoggedIn(account: Account) extends Event

final case class Deactivated(account: Account) extends Event
final case class Reactivated(account: Account) extends Event

final case class AccountAdded(account: Account) extends Event
final case class AccountUpdate(account: Account) extends Event