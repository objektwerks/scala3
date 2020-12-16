package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Camera {
  def takePhoto(): Boolean = true
}

sealed trait Phone {
  def makeCall(): Boolean = true
}

class MobilePhone extends Camera with Phone

def useMobilePhone(mobilePhone: Camera & Phone): (Boolean, Boolean) = ( mobilePhone.takePhoto(), mobilePhone.makeCall() )

class IntersectionTypeTest extends AnyFunSuite with Matchers {
  test("intersection") {
    useMobilePhone( MobilePhone() ) shouldBe (true, true)
  }
}