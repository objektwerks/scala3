package objektwerks

import munit._

trait SemiGroup[T]:
  extension (x: T) def combine (y: T): T

trait Monoid[T] extends SemiGroup[T]:
  def unit: T

given Monoid[String]:
  extension (x: String) def combine (y: String): String = x.concat(y)
  def unit: String = ""

given Monoid[Int]:
  extension (x: Int) def combine (y: Int): Int = x + y
  def unit: Int = 0

def combineAll[T: Monoid](xs: List[T]): T = xs.foldLeft( summon[Monoid[T]].unit )( _.combine(_) )

class TypeClassTest extends FunSuite {
  test("type class") {
    val strings = List("This ", "is ", "insane!")
    assert( combineAll(strings) == "This is insane!" )

    val ints = List(1, 2, 3)
    assert( combineAll(ints) == 6 )
  }
}