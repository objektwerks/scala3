package objektwerks

import munit._

sealed trait Camera {
  def takePhoto(): Boolean = true
}

sealed trait Phone {
  def makeCall(): Boolean = true
}

class MobilePhone extends Camera with Phone

def useDevice(device: Camera & Phone): (Boolean, Boolean) = ( device.takePhoto(), device.makeCall() )

class IntersectionTypeTest extends FunSuite {
  test("intersection") {
    assert( useDevice( MobilePhone() ) == (true, true) )
  }
}