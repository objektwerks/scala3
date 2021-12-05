package objektwerks.model

sealed trait Event

final case class Registering() extends Event
final case class LoggedIn(account: Account) extends Event

final case class Deactivated(account: Account) extends Event
final case class Reactivated(account: Account) extends Event

final case class EntityListed(entities: Seq[Entity]) extends Event
final case class EntityAdded(entity: Entity) extends Event
case object EntityUpdated extends Event