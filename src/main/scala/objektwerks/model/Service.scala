package objektwerks.model

import scala.concurrent.Future

trait Service:
  def register(register: Register): Future[Either[Fault, Registering]]
  def login(login: Login): Future[Either[Fault, LoggedIn]]
  def deactivate(deactivate: Deactivate): Future[Either[Fault, Deactivated]]
  def reactivate(reactivate: Reactivate): Future[Either[Fault, Reactivated]]
  def listEntity(entity: Entity): Future[Either[Fault, EntityListed]]
  def addEntity(entity: Entity): Future[Either[Fault, EntityAdded]]
  def updateEntity(entity: Entity): Future[Either[Fault, EntityUpdated]]