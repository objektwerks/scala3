package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class IntersectionTypeTest extends AnyFunSuite with Matchers:
  sealed trait Camera:
    def cameraEnabled: Boolean = true

  sealed trait Mic:
    def micEnabled: Boolean = true

  open class BasicPhone extends Camera with Mic

  open class SuperPhone

  def verifyMobilePhone(mobilePhone: Camera & Mic): Boolean = mobilePhone.cameraEnabled && mobilePhone.micEnabled

  test("intersection") {
    verifyMobilePhone( new BasicPhone ) shouldBe true
    verifyMobilePhone( new SuperPhone with Camera with Mic ) shouldBe true
  }