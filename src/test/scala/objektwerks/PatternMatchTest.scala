package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

class PatternMatchTest extends AnyFunSuite with Matchers:
  test("variable") {
    final case class Order(product: String, quantity: Int)
    def byVariable(order: Order): (String, Int) = order match {
      case Order(p, q) => (p, q)
    }

    val (product, quanity) = byVariable(Order("beer", 6))
    product shouldBe "beer"
    quanity shouldBe 6
  }

  test("type") {
    def byType(t: Any): String = t match {
      case i: Int => s"integer: $i"
      case d: Double => s"double: $d"
      case s: String => s"string: $s"
    }

    byType(1) shouldBe "integer: 1"
    byType(1.0) shouldBe "double: 1.0"
    byType("10") shouldBe "string: 10"
  }

  test("tuple") {
    def byTuple(t: (Int, Int)): Int = t match {
      case (a, b) => a + b
    }

    byTuple((1, 2)) shouldBe 3
  }

  test("or") {
    def isTrue(a: Any) = a match {
      case 0 | "" => false
      case _ => true
    }

    isTrue(1) shouldBe true
    isTrue(0) shouldBe false
    isTrue("") shouldBe false
  }

  test("case class") {
    final case class Person(name: String)
    def byPerson(p: Person): String = p match {
      case Person("John") => "Mr. " + p.name
      case Person("Jane") => "Ms. " + p.name
      case Person(name) => s"Mr. $name"
    }

    byPerson(Person("John")) shouldBe "Mr. John"
    byPerson(Person("Jane")) shouldBe "Ms. Jane"
    byPerson(Person("Jake")) shouldBe "Mr. Jake"
  }

  test("tailrec sum") {
    @tailrec
    def sum(numbers: List[Int], acc: Int = 0): Int = numbers match {
      case Nil => acc
      case head :: tail => sum(tail, acc + head)
    }

    sum(Nil) shouldBe 0
    sum(List(1, 2, 3)) shouldBe 6
  }

  test("guarded") {
    val m3m5 = ArrayBuffer[String]()
    val m3 = ArrayBuffer[String]()
    val m5 = ArrayBuffer[String]()
    val none = ArrayBuffer[String]()
    1 until 100 foreach {
      case i if i % 3 == 0 && i % 5 == 0 => m3m5 += s"$i -> m3 & m5"
      case i if i % 3 == 0 => m3 += s"$i -> m3"
      case i if i % 5 == 0 => m5 += s"$i -> m5"
      case i => none += i.toString
    }

    m3m5.size shouldBe 6
    m3.size shouldBe 27
    m5.size shouldBe 13
    none.size shouldBe 53
    m3m5.size + m3.size + m5.size + none.size shouldBe 99
  }

  test("alias") {
    final case class Stock(symbol: String, price: Double)
    def isPriceHigher(today: Stock, yesterday: Stock): Boolean = today match {
      case t @ Stock(_, _) if t.symbol == yesterday.symbol => t.price > yesterday.price
      case _ => false
    }

    val today = Stock("XYZ", 3.33)
    val yesterday = Stock("XYZ", 1.11)
    isPriceHigher(today, yesterday) shouldBe true
  }

  test("regex") {
    val ipAddress = Regex("""(\d+)\.(\d+)\.(\d+)\.(\d+)""")
    def byRegex(address: String): (Int, Int, Int, Int) = address match {
      case ipAddress(a, b, c, d) => (a.toInt, b.toInt, c.toInt, d.toInt)
    }
    
    (10, 10, 0, 1) shouldBe byRegex("10.10.0.1")
  }