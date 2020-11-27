package objektwerks

import munit._

trait SemiGroup[T]:
  extension (a: T) def join (b: T): T

trait Monoid[T] extends SemiGroup[T]:
  def unit: T

given Monoid[String]:
  extension (a: String) def join (b: String): String = a.concat(b)
  def unit: String = ""

given Monoid[Int]:
  extension (a: Int) def join (b: Int): Int = a + b
  def unit: Int = 0

def joinAll[T: Monoid](ts: Seq[T]): T = ts.foldLeft( summon[Monoid[T]].unit )( _.join(_) )

class TypeClassTest extends FunSuite {
  test("type class") {
    val strings = Seq("This ", "is ", "insane!")
    assert( joinAll(strings) == "This is insane!" )

    val ints = Seq(1, 2, 3)
    assert( joinAll(ints) == 6 )
  }
}