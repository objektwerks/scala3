package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Camera:
  def cameraEnabled(): Boolean = true

sealed trait Phone:
  def phoneEnabled(): Boolean = true

class MobilePhone extends Camera with Phone

def verifyMobilePhone(mobilePhone: Camera & Phone): (Boolean, Boolean) = ( mobilePhone.cameraEnabled(), mobilePhone.phoneEnabled() )

class IntersectionTypeTest extends AnyFunSuite with Matchers {
  test("intersection") {
    verifyMobilePhone( MobilePhone() ) shouldBe (true, true)
  }
}