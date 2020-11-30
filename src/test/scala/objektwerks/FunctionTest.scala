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

  test("def expression") {
    def isEven(i: Int): Boolean = i % 2 == 0
    assert( isEven(2) == true )
  }

  test("def block") {
    def isOdd(i: Int): Boolean = {
      i % 2 != 0
    }
    assert( isOdd(3) == true )
  }

  test("def match") {
    def sum(xs: List[Int]): Int = xs match {
      case Nil => 0
      case head :: tail => head + sum(tail)
    }
    assert( sum(List(1, 2, 3)) == 6 )
  }

  test("currying") {
    def greeting(greeting: String): String => String = (name: String) => {
      greeting + ", " + name + "!"
    }
    val hello = greeting("Hello")
    assert( hello("John") == "Hello, John!" )
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
    val product = multiplier _
    val multiplyByFive = multiplier(5, _: Int)
    assert( product(5, 20) == 100 )
    assert( multiplyByFive(20) == 100 )
  }

  test("partial function") {
    val multipleByOne: PartialFunction[Int, Int] = {
      case i: Int if i != 0 => i * 1
    }
    assert( ( Try { List(0, 1, 2) map multipleByOne }.isFailure ) == true )
    assert( ( List(0, 1, 2) collect multipleByOne ) == List(1, 2) )

    val divideByOne = new PartialFunction[Int, Int] {
      def apply(i: Int): Int = i / 1
      def isDefinedAt(i: Int): Boolean = i != 0
    }
    assert( divideByOne(2) == 2 )
    assert( divideByOne(0) == 0 )
  }

  test("curry") {
    def multiply(x: Int): Int => Int = (y: Int) => x * y
    assert( multiply(3)(3) == 9 )

    def add(x: Int)(y: Int): Int = x + y
    assert( add(1)(2) == 3 )
  }

  test("curried") {
    val sum = (x: Int, y: Int) => x + y
    val curriedSum = sum.curried
    assert( curriedSum(1)(2) == 3 )
  }

  test ("lambda") {
    val lambdaFilteredList = List(1, 2, 3, 4).filter(_ % 2 == 0)
    assert( lambdaFilteredList == List(2, 4) )
  }

  test("non-tailrec") {
    def factorial(n: Int): Int = n match {
      case i if i < 1 => 1
      case _ => n * factorial(n - 1)
    }
    assert( factorial(3) == 6 )
  }

  test("tailrec") {
    @tailrec
    def factorial(n: Int, acc: Int = 1): Int = n match {
      case i if i < 1 => acc
      case _ => factorial(n - 1, acc * n)
    }
    assert( factorial(9) == 362880 )
  }

  test("pure function") {
    def add(x: Int, y: Int): Int = {
      x + y // No side-effecting IO.
    }
    assert( add(1, 2) == 3 )
  }

  test("impure function") {
    def add(x: Int, y: Int): Int = {
      val sum = x + y
      println(s"side-effeecting impure function println: $sum") // Simulating side-effecting IO
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

    val xs = (1 to 10).toList
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
    val y = selectByIndex(ys, 5)
    val z = selectByIndex(zs, 5)

    assert( x.get == xs(5) )
    assert( y.isEmpty == true )
    assert( z.isEmpty == true )
  }

  test("intersection") {
    def intersection(source: List[Int], target: List[Int]): List[Int] = {
      for (i <- source if target.contains(i)) yield i
    }

    val xs = List.range(1, 10)
    val ys = List.range(1, 20)
    val zs = List.range(30, 40)

    assert( intersection(xs, ys) == xs.intersect(ys) )
    assert( intersection(ys, xs) == ys.intersect(xs) )
    assert( intersection(ys, zs) == ys.intersect(zs) )
  }
}