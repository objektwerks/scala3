package objektwerks.model

import cask.main.MainRoutes
import cask.model.Request
import cask.endpoints.post

object Server extends MainRoutes:
  @post("/command")
  def onCommand(request: Request) =
    request.text("Todo")

  initialize()