package objektwerks

import munit._

import scala.annotation.tailrec
import scala.language.postfixOps
import scala.util.{Random, Try}

class FunctionTest extends FunSuite {
  test("literal") {
    val add = (x: Int, y: Int) => x + y
    assert( add(3, 3) == 6 )

    val multiply = (x: Int, y: Int) => x * y: Int
    assert( multiply(3, 3) == 9 )

    val subtract: (Int, Int) => Int = (x, y) => x - y
    assert( subtract(9, 3) == 6 )
  }

  test("method") {
    def isEven(i: Int): Boolean = i % 2 == 0
    assert( isEven(2) == true )

    def isOdd(i: Int): Boolean = { // block
      i % 2 != 0
    }
    assert( isOdd(3) == true )

    def sum(xs: List[Int]): Int = xs match {
      case Nil => 0
      case head :: tail => head + sum(tail)
    }
    assert( sum(List(1, 2, 3)) == 6 )
  }

  test("curry") {
    def add(x: Int)(y: Int): Int = x + y
    assert( add(1)(2) == 3 )

    def multiply(x: Int): Int => Int = (y: Int) => x * y
    assert( multiply(3)(3) == 9 )

    def greeting(greeting: String): String => String = (name: String) => {
      greeting + ", " + name + "!"
    }
    val curriedHello = greeting("Hello")
    assert( curriedHello("John") == "Hello, John!" )

    val sum = (x: Int, y: Int) => x + y
    val curriedSum = sum.curried
    assert( curriedSum(1)(2) == 3 )
  }

  test("call by value") {
    def callByValue(random: Long): (Long, Long) = (random, random)

    val (randomResult, randomResultNext) = callByValue(Random.nextLong())
    assert( randomResult == randomResultNext )
  }

  test("call by name") {
    def callByName(random: => Long): (Long, Long) = (random, random)
    
    val (randomResult, randomResultNext) = callByName(Random.nextLong())
    assert( randomResult != randomResultNext )
  }

  test("default args") {
    def multiply(x: Int, y: Int = 1): Int = x * y
    assert( multiply(1) == 1 )
  }

  test("var args") {
    def add(varargs: Int*): Int = varargs.sum
    assert( add(1, 2, 3) == 6 )
    assert( add(List(1, 2, 3):_*) == 6 )
  }

  test("closure") {
    val legalDrinkingAge = 21
    def isLegalDrinkingAge(age: Int): Boolean = age >= legalDrinkingAge
    assert( isLegalDrinkingAge(22) == true )
  }

  test("higher order") {
    def square(f: Int => Int, i: Int) = f(i)
    assert( square((x: Int) => x * x, 2) == 4 )
  }

  test("partially applied") {
    def multiplier(x: Int, y: Int): Int = x * y
    val multiplyByFive = multiplier(5, _: Int)
    assert( multiplyByFive(20) == 100 )
  }

  test("partial function") {
    val multipleByOne: PartialFunction[Int, Int] = {
      case i: Int if i != 0 => i * 1
    }
    assert( ( Try { List(0, 1, 2) map multipleByOne }.isFailure ) == true )
    assert( ( List(0, 1, 2) collect multipleByOne ) == List(1, 2) )
    assert( ( List(42, "cat") collect { case i: Int => multipleByOne(i) } ) == List(42) )

    val divideByOne = new PartialFunction[Int, Int] {
      def apply(i: Int): Int = i / 1
      def isDefinedAt(i: Int): Boolean = i != 0
    }
    assert( divideByOne(2) == 2 )
    assert( divideByOne(0) == 0 )
    assert( divideByOne.isDefinedAt(3) == true )
    assert( divideByOne.isDefinedAt(0) == false )

    def isEven: PartialFunction[Int, String] = { case i if i % 2 == 0 => s"$i even" }
    def isOdd: PartialFunction[Int, String] = { case i if i % 2 == 1 => s"$i odd" }
    assert( ( 1 to 3 collect isEven ) == Vector("2 even") )
    assert( ( 1 to 3 collect isOdd ) == Vector("1 odd", "3 odd") )
    assert( ( 1 to 3 collect (isEven orElse isOdd) ) == Vector("1 odd", "2 even", "3 odd") )
  }

  test("non-tailrec") {
    def factorial(n: Int): Int = n match {
      case i if i < 1 => 1
      case _ => n * factorial(n - 1) // non-tailrec
    }
    assert( factorial(3) == 6 )
  }

  test("tailrec") {
    @tailrec
    def factorial(n: Int, acc: Int = 1): Int = n match {
      case i if i < 1 => acc
      case _ => factorial(n - 1, acc * n) // tailrec
    }
    assert( factorial(9) == 362880 )
  }

  test("pure function") {
    def add(x: Int, y: Int): Int = x + y // No side-effecting IO.
    assert( add(1, 2) == 3 )
  }

  test("impure function") {
    def add(x: Int, y: Int): Int = {
      val sum = x + y
      println(s"Side-effeecting impure function using println: $sum.")
      sum
    }
    assert( add(1, 2) == 3 )
  }

  test("compose > andThen") {
    val incr = (n: Int) => n + 1
    val decr = (n: Int) => n - 1

    val incrComposeDecr = incr compose decr
    val incrAndThenDecr = incr andThen decr

    val incrDecrAsList = List(incr, decr)
    val incrDecrAsListWithReduce = incrDecrAsList reduce ( _ andThen _ )

    val xs = 1 to 10 toList
    val ys = xs map incr map decr
    val zs = xs map incrComposeDecr map incrAndThenDecr
    val fs = xs map ( incrDecrAsList reduce ( _ compose _) )
    val gs = xs map ( incrDecrAsList reduce ( _ andThen _) )
    val us = xs map incrDecrAsListWithReduce

    assert( xs == ys )
    assert( ys == zs )
    assert( fs == zs )
    assert( gs == fs )
    assert( us == gs )
  }

  test("select by index") {
    def selectByIndex(source: List[Int], index: Int): Option[Int] = {
      @tailrec
      def loop(source: List[Int], index: Int, acc: Int = 0): Option[Int] = source match {
        case Nil => None
        case head :: tail => if (acc == index) Some(head) else loop(tail, index, acc + 1)
      }
      loop(source, index)
    }

    val xs = 1 to 10 toList
    val ys = List[Int]()
    val zs = List(1, 2, 3, 4)

    val x = selectByIndex(xs, 5)
    assert( x.get == xs(5) )

    val y = selectByIndex(ys, 5)
    assert( y.isEmpty == true )

    val z = selectByIndex(zs, 5)
    assert( z.isEmpty == true )
  }

  test("intersection") {
    def intersection[A](as: List[A], bs: List[A]): List[A] = {
      for (i <- as if bs.contains(i)) yield i
    }

    val xs = List.range(1, 10)
    val ys = List.range(1, 20)
    val zs = List.range(30, 40)

    assert( intersection(xs, ys) == xs.intersect(ys) )
    assert( intersection(ys, xs) == ys.intersect(xs) )
    assert( intersection(ys, zs) == ys.intersect(zs) )
  }

  test("does List A contain List B") {
    def doesListAcontainListB[A](listA: List[A], listB: List[A]): Boolean = {
      listB.count(b => listA.contains(b)) == listB.length
    }

    assert( doesListAcontainListB( listA = (1 to 20).toList, listB = (5 to 15).toList ) == true )
    assert( doesListAcontainListB( listA = (15 to 50).toList, listB = (10 to 30).toList ) == false )
  }

  test("timer") {
    def timer[A](codeblock: => A): (A, Double) = {
      val startTime = System.nanoTime
      val result = codeblock
      val stopTime = System.nanoTime
      val delta = stopTime - startTime
      (result, delta/1000000d)
    }

    val (result, time) = timer { factorial(19) }
    assert( result == 109641728)
    assert( time > 0.0)
  }

  test("diff as percentage") {
    def diffAsPercentage(previous: Double, current: Double): Int = {
      val dividend = current - previous
      val divisor = ( current + previous ) / 2
      val delta = Math.abs( dividend / divisor ) * 100
      delta.round.toInt
    }

    assert( diffAsPercentage(70.0, 75.0) == 7 )
    assert( diffAsPercentage(75.0, 70.0) == 7 )
    assert( diffAsPercentage(75.0, 80.0) == 6 )
  }
}