package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

class PatternMatchTest extends AnyFunSuite with Matchers:
  test("variable") {
    final case class Order(product: String, quantity: Int)

    def byVariable(order: Order): (String, Int) = order match
      case Order(beer, quantity) => (beer, quantity)

    val (product, quanity) = byVariable(Order("Dogfish Head 60' IPA", 6))
    product shouldBe "Dogfish Head 60' IPA"
    quanity shouldBe 6
  }

  test("type") {
    def byType(tpe: Any): String = tpe match
      case int: Int => s"integer: $int"
      case double: Double => s"double: $double"
      case string: String => s"string: $string"

    byType(1) shouldBe "integer: 1"
    byType(1.0) shouldBe "double: 1.0"
    byType("10") shouldBe "string: 10"
  }

  test("tuple") {
    def byTuple(tuple: (Int, Int)): Int = tuple match
      case (x, y) => x + y

    byTuple((1, 2)) shouldBe 3
  }

  test("or") {
    def isEmpty(matchable: Matchable): Boolean = matchable match
      case 0 | "" => true
      case _ => false

    isEmpty(1) shouldBe false
    isEmpty(0) shouldBe true
    isEmpty("") shouldBe true
  }

  test("case class") {
    final case class Person(name: String)

    def byPerson(person: Person): String = person match
      case p @ Person("John") => "Mr. " + p.name
      case p @ Person("Jane") => "Ms. " + p.name
      case Person(name) => s"Mr. $name"

    byPerson(Person("John")) shouldBe "Mr. John"
    byPerson(Person("Jane")) shouldBe "Ms. Jane"
    byPerson(Person("Jake")) shouldBe "Mr. Jake"
  }

  test("tailrec") {
    @tailrec
    def sum(numbers: List[Int], acc: Int = 0): Int = numbers match
      case Nil => acc
      case head :: tail => sum(tail, acc + head)

    sum(Nil) shouldBe 0
    sum(List(1, 2, 3)) shouldBe 6
  }

  test("alias > guard") {
    final case class Stock(symbol: String, price: Double)

    def isPriceHigher(today: Stock, yesterday: Stock): Boolean = today match
      case t @ Stock(_, _) if t.symbol == yesterday.symbol => t.price > yesterday.price
      case _ => false

    val today = Stock("XYZ", 3.33)
    val yesterday = Stock("XYZ", 1.11)
    isPriceHigher(today, yesterday) shouldBe true
  }

  test("regex") {
    val ipAddress = Regex("""(\d+)\.(\d+)\.(\d+)\.(\d+)""")
    
    def byRegex(address: String): (Int, Int, Int, Int) = address match
      case ipAddress(a, b, c, d) => (a.toInt, b.toInt, c.toInt, d.toInt)
    
    (10, 10, 0, 1) shouldBe byRegex("10.10.0.1")
  }