package objektwerks

import munit._

trait Camera {
  def takePhoto(): Boolean = true
}

trait Phone {
  def makeCall(): Boolean = true
}

class MobilePhone extends Camera with Phone

def useDevice(device: Camera & Phone): (Boolean, Boolean) = ( device.takePhoto(), device.makeCall() )

class IntersectionTypeTest extends FunSuite {
  test("intersection") {
    assert( useDevice( MobilePhone() ) == (true, true) )
  }
}