package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.reflect.Selectable._

class Record(fields: (String, Any)*) extends Selectable {
  private val columns = fields.toMap
  def selectDynamic(column: String): Any = columns(column)
}

type Car = Record {
  val make: String
  val model: String
  val year: Int
}

class StructuralTypeTest extends AnyFunSuite with Matchers {
  test("structural") {
    val car = Record("make" -> "Porsche", "model" -> "911", "year" -> 1964).asInstanceOf[Car]
    val make = car.selectDynamic("make").asInstanceOf[String]
    val model = car.selectDynamic("model").asInstanceOf[String]
    val year = car.selectDynamic("year").asInstanceOf[Int]
    make shouldBe "Porsche"
    model shouldBe "911"
    year shouldBe 1964
  }
}