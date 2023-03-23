package objektwerks.function

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec
import scala.language.postfixOps
import scala.util.chaining.*
import scala.util.{Random, Try}

class FunctionTest extends AnyFunSuite with Matchers:
  test("literal") {
    val add = (x: Int, y: Int) => x + y
    add(3, 3) shouldBe 6

    val multiply = (x: Int, y: Int) => x * y: Int
    multiply(3, 3) shouldBe 9

    val subtract: (Int, Int) => Int = (x, y) => x - y
    subtract(9, 3) shouldBe 6
  }

  test("method") {
    def isEven(i: Int): Boolean = i % 2 == 0
    isEven(2) shouldBe true

    def isOdd(i: Int): Boolean = { // block
      i % 2 != 0
    }
    isOdd(3) shouldBe true

    def sum(xs: List[Int]): Int = xs match {
      case Nil => 0
      case head :: tail => head + sum(tail)
    }
    sum(List(1, 2, 3)) shouldBe 6
  }

  test("curry") {
    def add(x: Int)(y: Int): Int = x + y
    add(1)(2) shouldBe 3

    def multiply(x: Int): Int => Int = (y: Int) => x * y
    multiply(3)(3) shouldBe 9

    def greeting(greeting: String): String => String = (name: String) => greeting + ", " + name + "!"
    val curriedHello = greeting("Hello")
    curriedHello("John") shouldBe "Hello, John!"

    val sum = (x: Int, y: Int) => x + y
    val curriedSum = sum.curried
    curriedSum(1)(2) shouldBe 3
  }

  test("call by value") {
    def callByValue(random: Long): (Long, Long) = (random, random)

    val (randomResult, randomResultNext) = callByValue(Random.nextLong())
    randomResult shouldEqual randomResultNext
  }

  test("call by name") {
    def callByName(random: => Long): (Long, Long) = (random, random)
    
    val (randomResult, randomResultNext) = callByName(Random.nextLong())
    randomResult should not equal randomResultNext
  }

  test("default args") {
    def multiply(x: Int, y: Int = 1): Int = x * y
    multiply(1) shouldBe 1
  }

  test("var args") {
    def add(varargs: Int*): Int = varargs.sum
    add(1, 2, 3) shouldBe 6
    add(List(1, 2, 3):_*) shouldBe 6
  }

  test("closure") {
    val legalDrinkingAge = 21
    def isLegalDrinkingAge(age: Int): Boolean = age >= legalDrinkingAge
    isLegalDrinkingAge(22) shouldBe true
  }

  test("higher order") {
    def applyFunction(f: Int => Int, i: Int) = f(i)
    applyFunction((x: Int) => x * x, 2) shouldEqual 4

    def cube(n: Int): Int = n * n * n
    List(1, 2, 3).map(cube) shouldBe List(1, 8, 27)
  }

  test("partially applied") {
    def multiplier(x: Int, y: Int): Int = x * y
    val multiplyByFive = multiplier(5, _: Int)
    multiplyByFive(20) shouldBe 100
  }

  test("partial function") {
    val multipleByOne: PartialFunction[Int, Int] =
      case i: Int if i != 0 => i * 1
    
    Try { List(0, 1, 2) map multipleByOne }.isFailure shouldBe true
    ( List(0, 1, 2) collect multipleByOne ) shouldBe List(1, 2)
    ( List(4, "cat") collect { case i: Int => multipleByOne(i) } ) shouldBe List(4)

    val divideByOne = new PartialFunction[Int, Int] {
      def apply(i: Int): Int = i / 1
      def isDefinedAt(i: Int): Boolean = i != 0
    }
    divideByOne(2) shouldBe 2
    divideByOne(0) shouldBe 0
    divideByOne.isDefinedAt(3) shouldBe true
    divideByOne.isDefinedAt(0) shouldBe false

    def isEven: PartialFunction[Int, String] = { case i if i % 2 == 0 => s"$i even" }
    def isOdd: PartialFunction[Int, String] = { case i if i % 2 == 1 => s"$i odd" }
    ( 1 to 3 collect isEven ) shouldBe Vector("2 even")
    ( 1 to 3 collect isOdd ) shouldBe Vector("1 odd", "3 odd")
    ( 1 to 3 collect (isEven orElse isOdd) ) shouldBe Vector("1 odd", "2 even", "3 odd")
  }

  test("non-tailrec") {
    def factorial(n: Int): Int = n match
      case i if i < 1 => 1
      case _ => n * factorial(n - 1) // non-tailrec
    
    factorial(3) shouldBe 6
  }

  test("tailrec") {
    @tailrec
    def factorial(n: Int, acc: Int = 1): Int = n match
      case i if i < 1 => acc
      case _ => factorial(n - 1, acc * n) // tailrec
    
    factorial(9) shouldBe 362880
  }

  test("pure function") {
    def add(x: Int, y: Int): Int = x + y // No side-effecting IO.
    add(1, 2) shouldBe 3
  }

  test("impure function") {
    def add(x: Int, y: Int): Int =
      val sum = x + y
      println(s"Side-effeecting impure function using println: $sum.")
      sum

    add(1, 2) shouldBe 3
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

    xs shouldBe ys
    ys shouldBe zs
    fs shouldBe zs
    gs shouldBe fs
    us shouldBe gs
  }

  test("pipe") {
    val square = (n: Int) => n * n
    2.pipe(square).tap(i => i shouldBe 4) shouldBe 4
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
    x.get shouldBe xs(5)

    val y = selectByIndex(ys, 5)
    y.isEmpty shouldBe true

    val z = selectByIndex(zs, 5)
    z.isEmpty shouldBe true
  }

  test("intersection") {
    def intersection[A](as: List[A], bs: List[A]): List[A] = for (i <- as if bs.contains(i)) yield i

    val xs = List.range(1, 10)
    val ys = List.range(1, 20)
    val zs = List.range(30, 40)

    intersection(xs, ys) shouldBe xs.intersect(ys)
    intersection(ys, xs) shouldBe ys.intersect(xs)
    intersection(ys, zs) shouldBe ys.intersect(zs)
  }

  test("does List A contain List B") {
    def doesListAcontainListB[A](listA: List[A], listB: List[A]): Boolean =
      listB.count(b => listA.contains(b)) == listB.length

    doesListAcontainListB( listA = (1 to 20).toList, listB = (5 to 15).toList ) shouldBe true
    doesListAcontainListB( listA = (15 to 50).toList, listB = (10 to 30).toList ) shouldBe false
  }

  test("timer") {
    @tailrec
    def factorial(n: Int, acc: Int = 1): Int = n match
      case i if i < 1 => acc
      case _ => factorial(n - 1, acc * n)

    def timer[A](codeblock: => A): (A, Double) =
      val startTime = System.nanoTime
      val result = codeblock
      val stopTime = System.nanoTime
      val delta = stopTime - startTime
      (result, delta/1000000d)

    val (result, time) = timer { factorial(19) }
    result shouldBe 109641728
    time > 0.0
  }

  test("diff as percentage") {
    def diffAsPercentage(previous: Double, current: Double): Int =
      val dividend = current - previous
      val divisor = ( current + previous ) / 2
      val delta = Math.abs( dividend / divisor ) * 100
      delta.round.toInt

    diffAsPercentage(70.0, 75.0) shouldBe 7
    diffAsPercentage(75.0, 70.0) shouldBe 7
    diffAsPercentage(75.0, 80.0) shouldBe 6
  }