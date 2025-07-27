package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait FutureMagnet:
  type Result

  def apply() : Result

def completeFuture(magnet: FutureMagnet):magnet.Result = magnet()

final class MagnetTypeTest extends AnyFunSuite with Matchers:
  test("pattern"):
    println("todo")