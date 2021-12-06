package objektwerks.model

sealed trait Event

final case class Registering() extends Event
final case class LoggedIn(account: Account) extends Event

final case class Deactivated(account: Account) extends Event
final case class Reactivated(account: Account) extends Event

final case class Listed(entities: Seq[Entity]) extends Event
final case class Added(entity: Entity) extends Event
final case class Updated(entity: Entity) extends Event