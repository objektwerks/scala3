package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Camera:
  def cameraEnabled(): Boolean = true

sealed trait Mic:
  def micEnabled(): Boolean = true

final class MobilePhone extends Camera with Mic

def verifyMobilePhone(mobilePhone: Camera & Mic): (Boolean, Boolean) = ( mobilePhone.cameraEnabled(), mobilePhone.micEnabled() )

class IntersectionTypeTest extends AnyFunSuite with Matchers:
  test("intersection") {
    verifyMobilePhone( MobilePhone() ) shouldBe (true, true)
  }