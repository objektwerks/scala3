package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.reflect.Selectable.*

final class StructuralTypeTest extends AnyFunSuite with Matchers:
  class CarBuilder(fields: (String, Any)*) extends Selectable:
    private val columns = fields.toMap
    def selectDynamic(column: String): Any = columns(column)

  type Car = CarBuilder:
    val make: String
    val model: String
    val year: Int

  test("structural") {
    val auto = CarBuilder("make" -> "Porsche", "model" -> "911", "year" -> 1964).asInstanceOf[Car]
    val make = auto.selectDynamic("make").asInstanceOf[String]
    val model = auto.selectDynamic("model").asInstanceOf[String]
    val year = auto.selectDynamic("year").asInstanceOf[Int]

    make shouldBe "Porsche"
    model shouldBe "911"
    year shouldBe 1964
  }