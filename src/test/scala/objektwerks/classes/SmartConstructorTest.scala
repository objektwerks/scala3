package objektwerks.classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://tuleism.github.io/blog/2020/scala-smart-constructors/
  */
final class SmartConstructorTest extends AnyFunSuite with Matchers:
  // 1.
  sealed trait Email:
    def address: String
  object Email:
    def validate(newAddress: String): Option[Email] =
      if newAddress.contains("@") then
        Some(
          new Email:
            override def address: String = newAddress
        )
      else None

  // 2.
  sealed abstract case class Xmail private (address: String)
  object Xmail:
    def validate(newAddress: String): Option[Xmail] =
      if newAddress.contains("@") then Some( new Xmail(newAddress){} )
      else None

  // 3.
  final case class Ymail private (address: String)
  object Ymail:
    def validate(newAddress: String): Option[Ymail] =
      if newAddress.contains("@") then Some( Ymail(newAddress) )
      else None

  test("trait"):
    Email.validate("test@test.com").nonEmpty shouldBe true
    Email.validate("").isEmpty shouldBe true
    // no copy method!

  test("abstract case class private"):
    Xmail.validate("test@test.com").nonEmpty shouldBe true
    Xmail.validate("").isEmpty shouldBe true
    // no copy method!

  test("final case class private"):
    Ymail.validate("test@test.com").nonEmpty shouldBe true
    Ymail.validate("").isEmpty shouldBe true
    // no copy method!