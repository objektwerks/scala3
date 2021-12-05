package objektwerks

sealed trait Command

final case class Register(email: String) extends Command

final case class AddAccount(account: Account) extends Command
final case class UpdateAccount(account: Account) extends Command