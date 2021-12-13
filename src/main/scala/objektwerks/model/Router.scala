package objektwerks.model

import cask.main.MainRoutes
import cask.model.Request
import cask.endpoints.postJson

import upickle.default._

class Router extends MainRoutes:
  val store = Store()
  val service = Service(store)
  val dispatcher = Dispatcher(service)

  override def port: Int = 7272

  @postJson("/command")
  def onCommand(request: Request) =
    val command = read[Command](request.bytes)(using Serializer.commandRW)
    val event = dispatcher.dispatch(command)
    write(event)(using Serializer.eventRW)

  initialize()