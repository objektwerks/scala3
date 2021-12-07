package objektwerks.model

import scala.collection.mutable

object Store:
  private val accounts = mutable.Map.empty[String, Account]
  private val pools = mutable.Map.empty[Int, Pool]
  private val surfaces = mutable.Map.empty[Int, Surface]
  private val pumps = mutable.Map.empty[Int, Pump]

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
    pools.addOne(newPool.id, newPool).getOrElse(newPool.id, Pool())

  def updatePool(pool: Pool): Unit = pools.update(pool.id, pool)

  def listSurfaces(): Seq[Surface] = surfaces.values.to(Seq)

  def addSurface(surface: Surface): Surface =
    val newSurface = surface.copy(id = surfaces.size + 1)
    surfaces.addOne(newSurface.id, newSurface).getOrElse(newSurface.id, Surface())

  def updateSurface(surface: Surface): Unit = surfaces.update(surface.id, surface)

  def listPumps(): Seq[Pump] = pumps.values.to(Seq)

  def addPump(pump: Pump): Pump =
    val newPump = pump.copy(id = pumps.size + 1)
    pumps.addOne(newPump.id, newPump).getOrElse(newPump.id, Pump())

  def updatePump(pump: Pump): Unit = pumps.update(pump.id, pump)