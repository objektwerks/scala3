package objektwerks.enums

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class EnumTest extends AnyFunSuite with Matchers:
  enum Gate(val state: Int):
    case closed extends Gate(0)
    case open extends Gate(1)

  enum Planet(val mass: Double, val radius: Double):
    val gravity = 6.67300E-11

    case Mercury extends Planet(3.303e+23, 2.4397e6)
    case Venus   extends Planet(4.869e+24, 6.0518e6)
    case Earth   extends Planet(5.976e+24, 6.37814e6)
    case Mars    extends Planet(6.421e+23, 3.3972e6)
    case Jupiter extends Planet(1.9e+27,   7.1492e7)
    case Saturn  extends Planet(5.688e+26, 6.0268e7)
    case Uranus  extends Planet(8.686e+25, 2.5559e7)
    case Neptune extends Planet(1.024e+26, 2.4746e7)

    def surfaceGravity: Double = gravity * mass / (radius * radius)
    def surfaceWeight(otherMass: Double): Double = otherMass * surfaceGravity

  test("enum") {
    val closed = Gate.closed
    closed shouldBe Gate.closed
    closed.ordinal shouldBe 0
    closed.state shouldBe 0
    Gate.valueOf("closed") shouldBe closed

    val open = Gate.open
    open shouldBe Gate.open
    open.ordinal shouldBe 1
    open.state shouldBe 1
    Gate.valueOf("open") shouldBe open

    val values = Gate.values
    values.length shouldBe 2
    values(0) shouldBe closed
    values(1) shouldBe open

    val mars = Planet.Mars
    mars.surfaceGravity shouldBe 3.7126290961053403
    mars.surfaceWeight(mars.mass) shouldBe 2.383879142609239E24
  }