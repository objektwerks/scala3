package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Camera:
  def cameraEnabled(): Boolean = true

sealed trait Mic:
  def micEnabled(): Boolean = true

open class MobilePhone

def verifyFeatures(mobilePhone: Camera & Mic): (Boolean, Boolean) = ( mobilePhone.cameraEnabled(), mobilePhone.micEnabled() )

class IntersectionTypeTest extends AnyFunSuite with Matchers:
  test("intersection") {
    verifyFeatures( new MobilePhone() with Camera with Mic ) shouldBe (true, true)
  }