package objektwerks

import munit._

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

class StructuralTypeTest extends FunSuite {
  test("structural") {
    val car = Record("make" -> "Porsche", "model" -> "911", "year" -> 1964).asInstanceOf[Car]
    val make = car.selectDynamic("make").asInstanceOf[String]
    val model = car.selectDynamic("model").asInstanceOf[String]
    val year = car.selectDynamic("year").asInstanceOf[Int]
    assert( make == "Porsche" )
    assert( model == "911" )
    assert( year == 1964)
  }
}