package objektwerks

import munit._

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

class MatchTest extends FunSuite {
  test("variable match") {
    final case class Order(product: String, quantity: Int)
    def byVariable(order: Order): (String, Int) = order match {
      case Order(p, q) => (p, q)
    }
    val (product, quanity) = byVariable(Order("beer", 6))
    assert( product == "beer" )
    assert( quanity == 6 )
  }

  test("type match") {
    def byType(t: Any): String = t match {
      case i: Int => s"integer: $i"
      case d: Double => s"double: $d"
      case s: String => s"string: $s"
    }
    assert( byType(1) == "integer: 1" )
    assert( byType(1.0) == "double: 1.0" )
    assert( byType("10") == "string: 10" )
  }

  test("tuple match") {
    def byTuple(t: (Int, Int)): Int = t match {
      case (a, b) => a + b
    }
    assert( byTuple((1, 2)) == 3 )
  }

  test("or match") {
    def isTrue(a: Any) = a match {
      case 0 | "" => false
      case _ => true
    }
    assert( isTrue(1) == true )
    assert( isTrue(0) == false )
    assert( isTrue("") == false )
  }

  test("case class match") {
    case class Person(name: String)
    def byPerson(p: Person): String = p match {
      case Person("John") => "Mr. " + p.name
      case Person("Jane") => "Ms. " + p.name
      case Person(name) => s"Mr. $name"
    }
    assert( byPerson(Person("John")) == "Mr. John" )
    assert( byPerson(Person("Jane")) == "Ms. Jane" )
    assert( byPerson(Person("Jake")) == "Mr. Jake" )
  }

  test("tailrec sum match") {
    @tailrec
    def sum(numbers: List[Int], acc: Int = 0): Int = numbers match {
      case Nil => acc
      case head :: tail => sum(tail, acc + head)
    }
    assert( sum(Nil) == 0 )
    assert( sum(List(1, 2, 3)) == 6 )
  }

  test("guarded match") {
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
    assert( m3m5.size == 6 )
    assert( m3.size == 27 )
    assert( m5.size == 13 )
    assert( none.size == 53 )
    assert( m3m5.size + m3.size + m5.size + none.size == 99)
  }

  test("alias match") {
    final case class Stock(symbol: String, price: Double)
    def isPriceHigher(today: Stock, yesterday: Stock): Boolean = today match {
      case t @ Stock(_, _) if t.symbol == yesterday.symbol => t.price > yesterday.price
      case _ => false
    }
    val today = Stock("XYZ", 3.33)
    val yesterday = Stock("XYZ", 1.11)
    assert( isPriceHigher(today, yesterday) == true )
  }

  test("regex match") {
    val ipAddress = new Regex("""(\d+)\.(\d+)\.(\d+)\.(\d+)""")
    def byRegex(address: String): (Int, Int, Int, Int) = address match {
      case ipAddress(a, b, c, d) => (a.toInt, b.toInt, c.toInt, d.toInt)
    }
    assert( (10, 10, 0, 1) == byRegex("10.10.0.1") )
  }
}