package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

object Pulses:
  opaque type Pulse = Int

  object Pulse:
    def apply(pulse: Int): Pulse = pulse

  extension (pulse: Pulse)
    def asInt: Int = pulse.toInt

class OpaqueTest extends AnyFunSuite with Matchers:
  test("opaque") {
    import Pulses._
    
    val pulse = Pulse(21)
    pulse shouldBe Pulse(21)
    pulse.asInt shouldBe 21
  }