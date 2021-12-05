package objektwerks

sealed trait Event

case object Registering extends Event
final case class LoggedIn(account: Account) extends Event

final case class Deactivated(account: Account) extends Event
final case class Reactivated(account: Account) extends Event

final case class AccountAdded(account: Account) extends Event
final case class AccountUpdate(account: Account) extends Event

final case class PoolsListed(pools: Seq[Pool]) extends Event
final case class PoolAdded(pool: Pool) extends Event
final case class PoolUpdated(pool: Pool) extends Event

final case class SurfacesListed() extends Event
final case class SurfaceAdded(surface: Surface) extends Event
final case class SurfaceUpdated(surface: Surface) extends Event