package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://macros.scala.school/phantom-types
  */
class PhantomTypeTest extends AnyFunSuite with Matchers:
  final case class Code[A] (value: String)

  extension (intCode: Code[Int])
    def add(that: Code[Int]): Code[Int] = Code(s"(${intCode.value} + ${that.value})")
    def multiply(that: Code[Int]): Code[Int] = Code(s"(${intCode.value} * ${that.value})")

  extension (boolCode: Code[Boolean])
    def and(that: Code[Boolean]): Code[Boolean] = Code(s"(${boolCode.value} && ${that.value})")
    def or(that: Code[Boolean]): Code[Boolean] = Code(s"(${boolCode.value} || ${that.value})")

  test("phantom"):
    println("todo")