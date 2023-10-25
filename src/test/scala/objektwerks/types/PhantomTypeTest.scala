package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://macros.scala.school/phantom-types
  */
class PhantomTypeTest extends AnyFunSuite with Matchers:
  final case class Code[A] private (value: String):
    def add(that: Code[Int]): Code[Int] = Code(s"($value + ${that.value})")

  object Code:
    def int(value: Int): Code[Int] = Code(value.toString)
    def bool(value: Boolean): Code[Boolean] = Code(value.toString)

  test("phantom"):
    println("todo")