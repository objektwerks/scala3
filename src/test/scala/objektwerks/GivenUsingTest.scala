package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

trait Combiner[T] {
  def combine(list: List[T]): T
}

given IntCombiner as Combiner[Int] {
  def combine(list: List[Int]): Int = list.sum
}

given StringCombiner as Combiner[String] {
  def combine(list: List[String]): String = list.mkString("")
}

def combineList[T](list: List[T])(using combiner: Combiner[T]): T = combiner.combine(list)

class GivenUsingTest extends AnyFunSuite with Matchers {
  test("given > using") {
    combineList( List(1, 2, 3) ) shouldBe 6
    combineList( List("Scala3 ", "is a ", "new language!") ) shouldBe "Scala3 is a new language!"
  }
}