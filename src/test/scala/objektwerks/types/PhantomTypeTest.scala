package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://macros.scala.school/phantom-types
  */
class PhantomTypeTest extends AnyFunSuite with Matchers:
  final case class Code[A] (value: String)

  extension (intCode: Code[Int])
    infix def add(that: Code[Int]): Code[Int] = Code(s"(${intCode.value} + ${that.value})")
    def multiply(that: Code[Int]): Code[Int] = Code(s"(${intCode.value} * ${that.value})")

  extension (boolCode: Code[Boolean])
    infix def and(that: Code[Boolean]): Code[Boolean] = Code(s"(${boolCode.value} && ${that.value})")
    def or(that: Code[Boolean]): Code[Boolean] = Code(s"(${boolCode.value} || ${that.value})")

  object Code:
    def int(value: Int): Code[Int] = Code(value.toString)
    def bool(value: Boolean): Code[Boolean] = Code(value.toString)

  test("phantom"):
    val oneCode = Code.int(1)
    val twoCode = Code.int(2)
    val threeCode = Code.int(3)
    val intCode = oneCode add twoCode add threeCode
    println(s"Phantom Type of Code[Int]: $intCode")
    intCode.value shouldBe "((1 + 2) + 3)"

    val falseCode = Code.bool(false)
    val trueCode = Code.bool(true)
    val booleanCode = falseCode and trueCode
    println(s"Phantom Type of Code[Boolean]: $booleanCode")
    booleanCode.value shouldBe "(false && true)"