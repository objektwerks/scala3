package objektwerks

import munit._

opaque type Pulse = Int
object Pulse {
  def apply(pulse: Int): Pulse = pulse
}
extension (pulse: Pulse) {
  def asInt: Int = pulse.toInt
  def asIntOption: Option[Pulse] = if (pulse > 0) Some(pulse) else None
  def asJson: String = """
                         { "pulse": 56 }
                       """
}

class OpaqueTest extends FunSuite {
  test("opaque") {
    val pulse = Pulse(56)
    assert( pulse == Pulse(56) )
    assert( pulse.asInt == 56 )
    assert( pulse.asIntOption == Some(pulse) )
    assert( pulse.asJson.contains("56") )
  }
}