package objektwerks.model

import scala.concurrent.Future

trait Service:
  def register(register: Register): Future[Registering]
  def login(login: Login): Future[LoggedIn]
  def deactivate(deactivate: Deactivate): Future[Deactivated]
  def reactivate(reactivate: Reactivate): Future[Reactivated]
  def listEntity(entity: Entity): Future[EntityListed]
  def addEntity(entity: Entity): Future[EntityAdded]
  def updateEntity(entity: Entity): Future[EntityUpdated]