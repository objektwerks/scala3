package objektwerks

import munit._

import scala.util.Try

def isGreaterThanZero(x: Int): Option[Int] = if (x > 0) Some(x) else None

class OptionTest extends FunSuite {
  test("option") {
    assert( isGreaterThanZero(1).get == 1 )
    assert( isGreaterThanZero(0).getOrElse(1) == 1 )
    assert( ( isGreaterThanZero(0) orElse Some(-1) contains -1 ) == true )

    assert( isGreaterThanZero(0).isEmpty == true )
    assert( isGreaterThanZero(1).nonEmpty == true )
    assert( isGreaterThanZero(1).isDefined == true )

    assert( ( isGreaterThanZero(1) collect { case n: Int => n * 3 } contains 3 ) == true )

    assert( isGreaterThanZero(1).contains(1) == true )
    assert( isGreaterThanZero(1).count(_ > 0) == 1 )

    assert( isGreaterThanZero(1).exists(_ > 0) == true )
    assert( ( isGreaterThanZero(1).filter(_ > 0) contains 1 ) == true )

    assert( isGreaterThanZero(1).forall(_ > 0) == true )
    isGreaterThanZero(3) foreach { v => assert( v == 3 ) }

    assert( isGreaterThanZero(1).fold(1)(_ * 3) == 3 )
  }

  test("match") {
    val x = isGreaterThanZero(1) match {
      case Some(n) => n * 3
      case None => -1
    }
    assert( x == 3 )
  }

  test("map") {
    assert( ( isGreaterThanZero(1) map(_ * 3) getOrElse(-1) ) == 3 )
  }

  test("flatmap") {
    def toInt(s: String): Option[Int] = Try(s.toInt).toOption

    val strings = List("1", "2", "3", "four")
    assert( strings.flatMap(toInt) == List(1, 2, 3) )
    assert( strings.flatMap(toInt).sum == 6 )

    def sum(x: Option[Int], y: Option[Int]): Option[Int] = x.flatMap(i => y.map(j => i + j))
    
    assert( sum(toInt("1"), toInt("2")).contains(3) == true )
    assert( sum(toInt("1"), toInt("zspace")).isEmpty == true )
  }
}