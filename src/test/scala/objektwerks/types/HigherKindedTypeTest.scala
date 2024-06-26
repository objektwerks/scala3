package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class HigherKindedTypeTest extends AnyFunSuite with Matchers:
  trait Functor[F[_]]:
    def map[A, B](fa: F[A])(f: A => B): F[B]

  test("higher kinded"):
    val listFunctor = new Functor[List]:
      override def map[A, B](xs: List[A])(f: A => B): List[B] = xs map f

    val numbers = List(1, 2, 3)
    val strings = listFunctor.map(numbers)(_.toString)
    strings shouldBe List("1", "2", "3")