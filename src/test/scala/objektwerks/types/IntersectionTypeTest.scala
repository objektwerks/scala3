package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class IntersectionTypeTest extends AnyFunSuite with Matchers:
  sealed trait Camera:
    def cameraEnabled: Boolean = true

  sealed trait Mic:
    def micEnabled: Boolean = true

  open class BasicPhone

  open class SuperPhone extends Camera with Mic

  def verifyMobilePhone(mobilePhone: Camera & Mic): Boolean = mobilePhone.cameraEnabled && mobilePhone.micEnabled

  test("intersection"):
    verifyMobilePhone( new BasicPhone with Camera with Mic ) shouldBe true
    verifyMobilePhone( new SuperPhone ) shouldBe true