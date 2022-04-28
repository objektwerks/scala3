package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TypeLambdaTest extends AnyFunSuite with Matchers:
  type MapByStringKey = [T] =>> Map[String, T]

  test("type lambda") {
    var map: MapByStringKey[String] = Map()
    map = map + ("a" -> "b")
    map.get("a") shouldBe Some("b")
  }