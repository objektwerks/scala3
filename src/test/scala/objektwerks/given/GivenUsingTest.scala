package objektwerks.`given`

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * Type Class pattern with given and using.
  */
final class GivenUsingTest extends AnyFunSuite with Matchers:
  sealed trait Combiner[T]:
    def combine(list: List[T]): T

  given Combiner[Int] with
    def combine(list: List[Int]): Int = list.sum

  given Combiner[String] with
    def combine(list: List[String]): String = list.mkString("")

  def combineList[T](list: List[T])(using combiner: Combiner[T]): T = combiner.combine(list)

  test("given > using") {
    combineList( List(1, 2, 3) ) shouldBe 6
    combineList( List("Scala3 ", "is a ", "new language!") ) shouldBe "Scala3 is a new language!"
  }