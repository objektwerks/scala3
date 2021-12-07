package objektwerks.model

import scala.collection.mutable

object Store:
  private val accounts = mutable.Map.empty[String, Account]
  private val pools = mutable.Map.empty[Int, Pool]

  def register(email: String): Account =
    val account = Account(email)
    accounts.addOne(account.license, account).getOrElse(account.license, Account())

  def login(email: String, pin: String): Option[Account] =
    accounts.values.find(account => account.email == email && account.pin == pin)

  def deactivate(license: String): Account =
    accounts.getOrElse(license, Account()).copy(deactivated = DateTime.currentDate, activated = 0)

  def reactivate(license: String): Account =
    accounts.getOrElse(license, Account()).copy(activated = DateTime.currentDate, deactivated = 0)

  def listPools(): Seq[Pool] = pools.values.to(Seq)

  def addPool(pool: Pool): Pool =
    val newPool = pool.copy(id = pools.size + 1)
    pools.addOne(newPool.id, pool).getOrElse(pool.id, Pool())

  def updatePool(pool: Pool): Unit = pools.update(pool.id, pool)