package objektwerks.extensions

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class ExtensionTest extends AnyFunSuite with Matchers:
  // No compiler error with open, final or nothing / blank!
  open case class Circle(x: Double, y: Double, radius: Double)

  extension (circle: Circle)
    def circumference: Double = circle.radius * math.Pi * 2

  test("extension"):
    val circle = Circle(3.0, 3.0, 3.0)
    circle.circumference shouldBe 18.84955592153876