package objektwerks

import munit._

sealed trait Camera {
  def takePhoto(): Boolean = true
}

sealed trait Phone {
  def makeCall(): Boolean = true
}

class MobilePhone extends Camera with Phone

def useMobilePhone(mobilePhone: Camera & Phone): (Boolean, Boolean) = ( mobilePhone.takePhoto(), mobilePhone.makeCall() )

class IntersectionTypeTest extends FunSuite {
  test("intersection") {
    assert( useMobilePhone( MobilePhone() ) == (true, true) )
  }
}