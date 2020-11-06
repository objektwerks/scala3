package objektwerks

import munit._

case class Circle(x: Double, y: Double, radius: Double)

extension (circle: Circle) {
  def circumference: Double = circle.radius * math.Pi * 2
}

class ExtensionTest extends FunSuite {
  test("extension") {
    val circle = Circle(3.0, 3.0, 3.0)
    assert( circle.circumference == 18.84955592153876 )
  }
}