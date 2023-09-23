package objektwerks.enums

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class EnumTest extends AnyFunSuite with Matchers:
  enum Exam:
    case Fail(val subject: String, val score: Int) extends Exam
    case Pass(val subject: String, val score: Int) extends Exam

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

  test("enum"):
    val fail: Exam.Fail = Exam.Fail("algebra", 50)
    val pass: Exam.Pass = Exam.Pass("chemistry", 100)
    fail.subject shouldBe "algebra"
    fail.score shouldBe 50
    pass.subject shouldBe "chemistry"
    pass.score shouldBe 100

    val closedGate = Gate.closed
    closedGate shouldBe Gate.closed
    closedGate.ordinal shouldBe 0
    Gate.fromOrdinal(0) shouldBe Gate.closed
    closedGate.state shouldBe 0
    Gate.valueOf("closed") shouldBe closedGate

    val openGate = Gate.open
    openGate shouldBe Gate.open
    openGate.ordinal shouldBe 1
    Gate.fromOrdinal(1) shouldBe Gate.open
    openGate.state shouldBe 1
    Gate.valueOf("open") shouldBe openGate

    val values = Gate.values
    values.length shouldBe 2
    values(0) shouldBe closedGate
    values(1) shouldBe openGate

    val mars = Planet.Mars
    mars.surfaceGravity shouldBe 3.7126290961053403
    mars.surfaceWeight(mars.mass) shouldBe 2.383879142609239E24