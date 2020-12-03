package objektwerks

import munit._

import scala.util.Try

class EitherTest extends FunSuite {
  def divide(x: Int, y: Int): Either[Throwable, Int] = Try(x / y).toEither

  test("either") {
    assert( divide(9, 3).isRight == true )
    assert( divide(9, 0).isLeft == true )
    assert( divide(9, 3).contains(3) == true )
    assert( divide(9, 3).exists(_ == 3) == true )
    assert( divide(9, 3).getOrElse(-1) == 3 )
    assert( divide(9, 3).map(_ * 3).getOrElse(-1) == 9 )
    assert( divide(9, 3).map(_ * 3).filterOrElse(_ == 9, -1).getOrElse(-1) == 9 )
    divide(3, 0) match {
      case Right(_) => throw new Exception("Should throw divide by zero error.")
      case Left(error) => error.isInstanceOf[Throwable] == true
    }
  }
}