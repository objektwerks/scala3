package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

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

def fibonacci(n: Long): Long = {
  @tailrec
  def loop(n: Long, a: Long, b: Long): Long = n match {
    case 0 => a
    case _ => loop(n - 1, b, a + b)
  }
  loop(n, 0, 1)
}

def fibonacci(ns: Seq[String]): Seq[Long] = {
  def toLong(s: String): Option[Long] = s.toLongOption
  ns.flatMap( n => toLong(n) ).map( n => fibonacci(n) )
}

@tailrec
final def intersectLists[A](listA: List[A],
                            listB: List[A],
                            acc: List[A] = List.empty[A]): List[A] =
  listA match {
    case Nil => acc
    case head :: tail =>
      if (listB.contains(head)) intersectLists(tail, listB, acc :+ head)
      else intersectLists(tail, listB, acc)
  }

class RecursionTest extends AnyFunSuite with Matchers {
  test("sum") {
    sum( Nil ) shouldBe 0
    sum( List(1, 2, 3) ) shouldBe 6
  }

  test("product") {
    product( Nil ) shouldBe 1
    product( List(1, 2, 3) ) shouldBe 6
  }

  test("reverse") {
    reverse( List(1, 2, 3) ) shouldBe List(3, 2, 1)
  }

  test("find nth element from right") {
    val xs = (1 to 10).toList
    findNthElementFromRight(xs, 4) shouldBe Some(7)
    findNthElementFromRight(xs, 15) shouldBe None
  }

  test("factorial") {
    factorial(3) shouldBe 6
  }

  test("fibbonaci") {
    fibonacci(39) shouldBe 63245986
    fibonacci( Seq("3", "6", "9", "four") ) shouldBe Seq(2L, 8L, 34L)
  }

  test("intersect lists") {
    val listA = (1 to 10).toList
    val listB = (5 to 15).toList
    val listIntersection = List(5, 6, 7, 8, 9, 10)
    intersectLists(listA, listB) shouldBe listIntersection
    ( listA intersect listB ) shouldBe listIntersection
  }
}