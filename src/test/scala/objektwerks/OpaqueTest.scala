package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

object Pulses:
  opaque type Pulse = Int

  object Pulse:
    def apply(pulse: Int): Pulse = pulse

  extension (pulse: Pulse)
    def asInt: Int = pulse

  extension (pulses: List[Pulse])
    def avg: Double = pulses.sum / pulses.length

class OpaqueTest extends AnyFunSuite with Matchers:
  test("opaque") {
    import Pulses._
    
    val pulse = Pulse(59)
    pulse shouldBe Pulse(59)
    pulse.asInt shouldBe 59

    val pulses = List(Pulse(59), Pulse(60), Pulse(61))
    pulses.avg shouldBe 60
  }