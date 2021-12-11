package objektwerks.model

import java.time._

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import DateTime._

class DateTimeTest extends AnyFunSuite with Matchers:
  test("low date") {
    val localDate = LocalDate.of(2001, 1, 1)
    localDateToInt(localDate) shouldEqual 20010101
    localDateToInt(2001, 1, 1) shouldEqual 20010101
    localDateToString(localDate) shouldEqual "2001-01-01"
    localDateAsStringToInt(localDateToString(localDate)) shouldEqual 20010101
    localDateAsIntToString(localDateToInt(localDate)) shouldEqual "2001-01-01"
  }

  test("high date") {
    val localDate = LocalDate.of(2001, 12, 15)
    localDateToInt(localDate) shouldEqual 20011215
    localDateToInt(2001, 12, 15) shouldEqual 20011215
    localDateToString(localDate) shouldEqual "2001-12-15"
    localDateAsStringToInt(localDateToString(localDate)) shouldEqual 20011215
    localDateAsIntToString(localDateToInt(localDate)) shouldEqual "2001-12-15"
  }

  test("low time") {
    val localTime = LocalTime.of(3, 3)
    localTimeToInt(localTime) shouldEqual 303
    localTimeToInt(3, 3) shouldEqual 303
    localTimeToString(localTime) shouldEqual "03:03"
    localTimeAsStringToInt(localTimeToString(localTime)) shouldEqual 303
    localTimeAsIntToString(localTimeToInt(localTime)) shouldEqual "03:03"
  }

  test("high time") {
    val localTime = LocalTime.of(23, 33)
    localTimeToInt(localTime) shouldEqual 2333
    localTimeToInt(23, 33) shouldEqual 2333
    localTimeToString(localTime) shouldEqual "23:33"
    localTimeAsStringToInt(localTimeToString(localTime)) shouldEqual 2333
    localTimeAsIntToString(localTimeToInt(localTime)) shouldEqual "23:33"
  }