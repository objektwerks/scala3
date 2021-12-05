package objektwerks.model

sealed trait Command

final case class Register(email: String) extends Command
final case class Login(email: String, pin: String) extends Command

final case class Deactivate(license: String) extends Command
final case class Reactivate(license: String) extends Command

final case class ListEntity(entity: Entity) extends Command
final case class AddEntity(entity: Entity) extends Command
final case class UpdateEntity(enity: Entity) extends Command