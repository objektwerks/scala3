package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Try

def isGreaterThanZero(x: Int): Option[Int] = if (x > 0) Some(x) else None

class OptionTest extends AnyFunSuite with Matchers {
  test("option") {
    isGreaterThanZero(1).get shouldBe 1
    isGreaterThanZero(0).getOrElse(1) shouldBe 1
    ( isGreaterThanZero(0) orElse Some(-1) contains -1 ) shouldBe true

    isGreaterThanZero(0).isEmpty shouldBe true
    isGreaterThanZero(1).nonEmpty shouldBe true
    isGreaterThanZero(1).isDefined shouldBe true

    ( isGreaterThanZero(1) collect { case n: Int => n * 3 } contains 3 ) shouldBe true

    isGreaterThanZero(1).contains(1) shouldBe true
    isGreaterThanZero(1).count(_ > 0) shouldBe 1

    isGreaterThanZero(1).exists(_ > 0) shouldBe true
    ( isGreaterThanZero(1).filter(_ > 0) contains 1 ) shouldBe true

    isGreaterThanZero(1).forall(_ > 0) shouldBe true
    isGreaterThanZero(3) foreach { v => v shouldBe 3 }

    isGreaterThanZero(1).fold(1)(_ * 3) shouldBe 3
  }

  test("match") {
    val x = isGreaterThanZero(1) match {
      case Some(n) => n * 3
      case None => -1
    }
    x shouldBe 3
  }

  test("map") {
    ( isGreaterThanZero(1) map(_ * 3) getOrElse(-1) ) shouldBe 3
  }

  test("flatmap") {
    def toInt(s: String): Option[Int] = s.toIntOption

    val strings = List("1", "2", "3", "four")
    strings.flatMap(toInt) shouldBe List(1, 2, 3)
    strings.flatMap(toInt).sum shouldBe 6

    def sum(x: Option[Int], y: Option[Int]): Option[Int] = x.flatMap(i => y.map(j => i + j))
    
    sum(toInt("1"), toInt("2")).contains(3) shouldBe true
    sum(toInt("1"), toInt("zspace")).isEmpty shouldBe true
  }
}