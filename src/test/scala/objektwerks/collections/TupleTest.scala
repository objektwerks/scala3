package objektwerks.collections

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/**
  * See: https://blog.rockthejvm.com/practical-type-level-programming/
  */
class TupleTest extends AnyFunSuite with Matchers:
  final case class KeyValue(key: Int, value: Int):
    def tupled: (Int, Int) = (key, value)

  final case class CityStateZip(city: String, state: String, zip: Int):
    def tupled: (String, String, Int) = (city, state, zip)

  test("tuple"):
    val (first, last, age) = ("fred", "flintstone", 99)
    first shouldBe "fred"
    last shouldBe "flintstone"
    age shouldBe 99

  test("tuple copy"):
    (2, 2) shouldBe KeyValue(1, 1).tupled.copy(2, 2)

  test("tupled"):
    val cityStateZip = CityStateZip("sunavabeach", "florida", 12345)
    val (city, state, zip) = cityStateZip.tupled
    city shouldBe "sunavabeach"
    state shouldBe "florida"
    zip shouldBe 12345

  test("untupling"):
    val tuples = List( (1, 1), (2, 2), (3, 3) )
    val sums = tuples.map:
      (x, y) => x + y
    sums shouldBe List(2, 4, 6)

  test("product"):
    val cityStateZip = CityStateZip("sunavabeach", "florida", 12345)
    val productedTupled = Tuple.fromProductTyped(cityStateZip)
    println("Product element names:")
    productedTupled.productElementNames.foreach(println)
    println("Product element values:")
    productedTupled._1 shouldBe cityStateZip.city
    productedTupled._2 shouldBe cityStateZip.state
    productedTupled._3 shouldBe cityStateZip.zip