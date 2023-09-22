package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://blog.rockthejvm.com/scala-3-type-lambdas/
  */
final class TypeLambdaTest extends AnyFunSuite with Matchers:
  type MapByStringKey = [T] =>> Map[String, T]

  test("type lambda") {
    var map: MapByStringKey[Int] = Map()
    map = map + ("a" -> 1)
    map.get("a") shouldBe Some(1)
  }