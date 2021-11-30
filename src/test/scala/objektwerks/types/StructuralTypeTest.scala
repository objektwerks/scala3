package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.reflect.Selectable.*

class Record(fields: (String, Any)*) extends Selectable:
  private val columns = fields.toMap
  def selectDynamic(column: String): Any = columns(column)

type Auto = Record {
  val make: String
  val model: String
  val year: Int
}

class StructuralTypeTest extends AnyFunSuite with Matchers:
  test("structural") {
    val auto = Record("make" -> "Porsche", "model" -> "911", "year" -> 1964).asInstanceOf[Auto]

    val make = auto.selectDynamic("make").asInstanceOf[String]
    val model = auto.selectDynamic("model").asInstanceOf[String]
    val year = auto.selectDynamic("year").asInstanceOf[Int]
    
    make shouldBe "Porsche"
    model shouldBe "911"
    year shouldBe 1964
  }