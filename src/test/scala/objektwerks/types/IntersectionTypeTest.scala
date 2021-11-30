package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class IntersectionTypeTest extends AnyFunSuite with Matchers:
  sealed trait Camera:
    def cameraEnabled: Boolean = true

  sealed trait Mic:
    def micEnabled: Boolean = true

  open class MobilePhone

  def verifyMobilePhone(mobilePhone: Camera & Mic): Boolean = mobilePhone.cameraEnabled && mobilePhone.micEnabled

  test("intersection") {
    verifyMobilePhone( new MobilePhone with Camera with Mic ) shouldBe true
  }