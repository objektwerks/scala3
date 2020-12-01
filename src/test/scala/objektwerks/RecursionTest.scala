package objektwerks

import munit._

import scala.annotation.tailrec
import scala.util.Try

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

def findNthElementFromRight[A](list: List[A], nthElement: Int): Option[A] = {
  @tailrec
  def reverse(list: List[A], acc: List[A] = List.empty[A]): List[A] = list match {
    case Nil => acc
    case head :: tail => reverse(tail, head :: acc)
  }
  Try { reverse(list)(nthElement - 1) }.toOption
}

@tailrec
final def factorial(n: Int, acc: Int = 1): Int = n match {
  case i if i < 1 => acc
  case _ => factorial(n - 1, acc * n)
}

def fibonacci(n: Long): BigInt = {
  @tailrec
  def loop(n: Long, a: Long, b: Long): BigInt = n match {
    case 0 => a
    case _ => loop(n - 1, b, a + b)
  }
  loop(n, 0, 1)
}

@tailrec
final def intersectLists[A](listA: List[A],
                            listB: List[A],
                            acc: List[A] = List.empty[A]): List[A] =
  listA match {
    case Nil => acc
    case head :: tail =>
      if (listB.contains(head)) {
        intersectLists(tail, listB, acc :+ head)
      } else {
        intersectLists(tail, listB, acc)
      }
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

  test("find nth element from right") {
    val xs = (1 to 10).toList
    assert( findNthElementFromRight(xs, 4) == Some(7) )
    assert( findNthElementFromRight(xs, 15) == None )
  }

  test("factorial") {
    assert( factorial(3) == 6 )
  }

  test("fibbonaci") {
    assert( fibonacci(39) == 63245986 )
  }

  test("intersect lists") {
    val listA = (1 to 10).toList
    val listB = (5 to 15).toList
    val listIntersection = List(5, 6, 7, 8, 9, 10)
    assert( intersectLists(listA, listB) == listIntersection )
    assert( ( listA intersect listB ) == listIntersection )
  }
}