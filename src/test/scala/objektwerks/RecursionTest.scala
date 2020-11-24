package objektwerks

import munit._

import scala.annotation.tailrec

@tailrec
def sum(xs: List[Int], acc: Int = 0): Int = xs match {
  case Nil => acc
  case head :: tail => sum(tail, acc + head)
}

@tailrec
def product(xs: List[Int], acc: Int = 1): Int = xs match {
  case Nil => acc
  case head :: tail => product(tail, acc * head)
}

@tailrec
def reverse[A](list: List[A], acc: List[A] = List.empty[A]): List[A] = list match {
  case Nil => acc
  case head :: tail => reverse(tail, head :: acc)
}

@tailrec
final def factorial(n: Int, acc: Int = 1): Int = n match {
  case i if i < 1 => acc
  case _ => factorial(n - 1, acc * n)
}

class RecursionTest extends FunSuite {
  test("sum") {
    assert( sum( List(1, 2, 3) ) == 6 )
  }

  test("product") {
    assert( product( List(1, 2, 3) ) == 6 )
  }

  test("reverse") {
    assert( reverse( List(1, 2, 3) ) == List(3, 2, 1) )
  }

  test("factorial") {
    assert( factorial(3) == 6 )
  }
}