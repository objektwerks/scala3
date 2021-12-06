package objektwerks.model

trait Service:
  def register(register: Register): Registering
  def login(login: Login): LoggedIn
  def deactivate(deactivate: Deactivate): Deactivated
  def reactivate(reactivate: Reactivate): Reactivated
  def listEntity(entity: Entity): EntityListed
  def addEntity(entity: Entity): EntityAdded
  def updateEntity(entity: Entity): EntityUpdated