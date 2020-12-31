package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

object Pulses {
  opaque type Pulse = Int

  object Pulse {
    def apply(pulse: Int): Pulse = pulse
  }

  extension (pulse: Pulse)
    def asInt: Int = pulse.toInt
    def asIntOption: Option[Pulse] = if (pulse > 0) Some(pulse) else None
    def asJson: String = s"""{ "pulse": ${pulse.asInt} }"""
}

class OpaqueTest extends AnyFunSuite with Matchers {
  test("opaque") {
    import Pulses._

    val pulse = Pulse(56)
    pulse shouldBe Pulse(56)
    pulse.asInt shouldBe 56
    pulse.asIntOption shouldBe Some(pulse)
    pulse.asJson shouldBe s"""{ "pulse": ${pulse.asInt} }"""
  }
}