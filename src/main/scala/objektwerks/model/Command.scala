package objektwerks.model

sealed trait Command

final case class Register(email: String) extends Command
final case class Login(email: String, pin: String) extends Command

final case class Deactivate(license: String) extends Command
final case class Reactivate(license: String) extends Command

final case class ListPools() extends Command
final case class AddPool(pool: Pool) extends Command
final case class UpdatePool(pool: Pool) extends Command

final case class ListSurfaces(poolId: Int) extends Command
final case class AddSurface(surface: Surface) extends Command
final case class UpdateSurface(surface: Surface) extends Command

final case class ListPumps(poolId: Int) extends Command
final case class AddPump(pump: Pump) extends Command
final case class UpdatePump(pump: Pump) extends Command