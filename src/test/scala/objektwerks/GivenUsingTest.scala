package objektwerks

import munit._

sealed trait Combiner[T] {
  def combine(list: List[T]): T
}

given IntCombiner as Combiner[Int] {
  def combine(list: List[Int]): Int = list.sum
}

given StringCombiner as Combiner[String] {
  def combine(list: List[String]): String = list.mkString("")
}

def combineList[T](list: List[T])(using combiner: Combiner[T]): T = combiner.combine(list)

class GivenUsingTest extends FunSuite {
  test("given > using") {
    assert( combineList( List(1, 2, 3) ) == 6 )
    assert( combineList( List("Scala3 ", "is a ", "new language!") ) == "Scala3 is a new language!" )
  }
}