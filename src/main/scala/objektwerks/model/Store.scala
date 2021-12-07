package objektwerks.model

import scala.collection.mutable

object Store:
  private val accounts = mutable.Map.empty[String, Account]

  def register(email: String): Account =
    val account = Account(email)
    accounts.addOne(account.license, account).getOrElse(account.license, Account())

  def login(email: String, pin: String): Option[Account] =
    accounts.values.find(account => account.email == email && account.pin == pin)

  def deactivate(license: String): Account =
    accounts.getOrElse(license, Account()).copy(deactivated = DateTime.currentDate, activated = 0)

  def reactivate(license: String): Account =
    accounts.getOrElse(license, Account()).copy(activated = DateTime.currentDate, deactivated = 0)