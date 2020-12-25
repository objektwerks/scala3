package objektwerks

import munit._

trait SemiGroup[T]:
  extension (a: T) def join (b: T): T

trait Monoid[T] extends SemiGroup[T]:
  def zero: T

given Monoid[String] with
  extension (a: String) def join (b: String): String = a.concat(b)
  def zero: String = ""

given Monoid[Int] with
  extension (a: Int) def join (b: Int): Int = a + b
  def zero: Int = 0

def joinAll[T: Monoid](ts: Seq[T]): T = ts.foldLeft( summon[Monoid[T]].zero )( _.join(_) )

class TypeClassTest extends FunSuite {
  test("type class") {
    val strings = Seq("Scala3 ", "is a ", "new language!")
    assert( joinAll(strings) == "Scala3 is a new language!" )

    val ints = Seq(1, 2, 3)
    assert( joinAll(ints) == 6 )
  }
}