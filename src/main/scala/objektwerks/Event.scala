package objektwerks

sealed trait Event

final case class AccountAdded(account: Account) extends Event
final case class AccountUpdate(account: Account) extends Event