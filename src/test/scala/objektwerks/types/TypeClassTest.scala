package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class TypeClassTest extends AnyFunSuite with Matchers:
  sealed trait SemiGroup[T]:
    extension (a: T) def join (b: T): T

  sealed trait Monoid[T] extends SemiGroup[T]:
    def zero: T

  given Monoid[String] with
    extension (a: String) def join (b: String): String = a.concat(b)
    def zero: String = ""

  given Monoid[Int] with
    extension (a: Int) def join (b: Int): Int = a + b
    def zero: Int = 0

  def joinAll[T: Monoid](ts: Seq[T]): T = ts.foldLeft( summon[Monoid[T]].zero )( _.join(_) )

  sealed trait Mimicable[A]:
    extension(a: A) def mimic: A

  given Mimicable[String] with
    extension (a: String) def mimic = a

  test("type class"):
    val strings = Seq("Scala3 ", "is a ", "new language!")
    joinAll(strings) shouldBe "Scala3 is a new language!"

    val ints = Seq(1, 2, 3)
    joinAll(ints) shouldBe 6

    "test".mimic shouldBe "test"