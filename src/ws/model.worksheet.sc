/* 
Object Model
------------
* Email(id, license, address, processed, valid)
* Account(license, email, pin, activated, deactivated)
* Pool(id, license, name, built, volume)
* Surface(id, poolId, installed, kind)
* Pump(id, poolId, installed, model)
* Timer(id, poolId, installed, model)
* TimerSetting(id, timerId, created, timeOn, timeOff)
* Heater(id, poolId, installed, model)
* HeaterSetting(id, heaterId, temp, dateOn, dateOff)
* Measurement(id, poolId, measured, temp, totalHardness, totalChlorine, totalBromine, freeChlorine, ph, totalAlkalinity, cyanuricAcid)
* Cleaning(id, poolId, cleaned, brush, net, vacuum, skimmerBasket, pumpBasket, pumpFilter, deck)
* Chemical(id, poolId, added, chemical, amount, unit)
* Supply(id, poolId, purchased, cost, supply, amount, unit)
* Repair(id, poolId, repaired, cost, repair)
* Fault(id, dateOf, timeOf, code, cause)
*/

import java.util.UUID
import scala.util.Random

sealed trait Entity extends Product with Serializable

final case class Email(id : String,
                       license: String,
                       address: String,
                       processed: Boolean = false,
                       valid: Boolean = false) extends Entity

final case class Account(license: String,
                         email: String,
                         pin: String,
                         activated: Int,
                         deactivated: Int) extends Entity {
  def deriveLicense: License = License(license)
}

object Account {
  private val specialChars = "~!@#$%^&*{}-+<>?/:;".toList
  private val random = new Random

  private def newLicense: String = UUID.randomUUID.toString
  private def newSpecialChar: Char = specialChars(random.nextInt(specialChars.length))
  private def newPin: String = Random.shuffle(
    Random
      .alphanumeric
      .take(7)
      .mkString
      .prepended(newSpecialChar)
      .appended(newSpecialChar)
  ).mkString

  val emptyAccount = Account(
    license = "",
    email = "",
    pin = "",
    activated = 0,
    deactivated = 0
  )
  val emptyLicense = ""
  val licenseHeader = "License"

  def apply(email: String): Account = Account(
    license = newLicense,
    email = email,
    pin = newPin,
    activated = DateTime.currentDate,
    deactivated = 0
  )
}

final case class License(key: String) extends Entity

final case class Pool(id: Int = 0,
                      license: String = "",
                      name: String = "",
                      built: Int = 0,
                      volume: Int = 1000) extends Entity

final case class PoolId(id: Int = 0) extends Entity

final case class Surface(id: Int = 0,
                         poolId: Int = 0,
                         installed: Int = 0,
                         kind: String = "") extends Entity

final case class Pump(id: Int = 0,
                      poolId: Int = 0,
                      installed: Int = 0,
                      model: String = "") extends Entity

final case class Timer(id: Int = 0,
                       poolId: Int = 0,
                       installed: Int = 0,
                       model: String = "") extends Entity

final case class TimerId(id: Int = 0) extends Entity

final case class TimerSetting(id: Int = 0,
                              timerId: Int = 0,
                              created: Int = 0,
                              timeOn: Int = 0,
                              timeOff: Int = 0) extends Entity

final case class Heater(id: Int = 0,
                        poolId: Int = 0,
                        installed: Int = 0,
                        model: String = "") extends Entity

final case class HeaterId(id: Int = 0) extends Entity

final case class HeaterSetting(id: Int = 0,
                               heaterId: Int = 0,
                               temp: Int = 0,
                               dateOn: Int = 0,
                               dateOff: Int = 0) extends Entity

final case class Measurement(id: Int = 0,
                             poolId: Int = 0,
                             measured: Int = 0,
                             temp: Int = 85,
                             totalHardness: Int = 375,
                             totalChlorine: Int = 3,
                             totalBromine: Int = 5,
                             freeChlorine: Int = 3,
                             ph: Double = 7.4,
                             totalAlkalinity: Int = 100,
                             cyanuricAcid: Int = 50) extends Entity

final case class Cleaning(id: Int = 0,
                          poolId: Int = 0,
                          cleaned: Int = 0,
                          brush: Boolean = true,
                          net: Boolean = true,
                          vacuum: Boolean = false,
                          skimmerBasket: Boolean = true,
                          pumpBasket: Boolean = false,
                          pumpFilter: Boolean = false,
                          deck: Boolean = false) extends Entity

final case class Chemical(id: Int = 0,
                          poolId: Int = 0,
                          added: Int = 0,
                          chemical: String = "",
                          amount: Double = 0.0,
                          unit: String = "") extends Entity

final case class Supply(id: Int = 0,
                        poolId: Int = 0,
                        purchased: Int = 0,
                        cost: Double = 0.0,
                        item: String = "",
                        amount: Double = 0.0,
                        unit: String = "") extends Entity

final case class Repair(id: Int = 0,
                        poolId: Int = 0,
                        repaired: Int = 0,
                        cost: Double = 0,
                        repair: String = "") extends Entity

final case class Fault(dateOf: Int,
                       timeOf: Int,
                       nanoOf: Int,
                       code: Int,
                       cause: String) extends Product with Serializable

object Fault {
  def apply(code: Int, cause: String): Fault = Fault(
    dateOf = DateTime.currentDate,
    timeOf = DateTime.currentTime,
    nanoOf = DateTime.nano,
    code = code,
    cause = cause
  )

  def apply(cause: String): Fault = Fault(
    dateOf = 0,
    timeOf = 0,
    nanoOf = 0,
    code = 0,
    cause = cause
  )
}

import java.time._

object DateTime {
  val dateFormatter = format.DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val timeFormatter = format.DateTimeFormatter.ofPattern("HH:mm")

  def currentDate: Int = localDateToInt(LocalDate.now)

  def localDateToInt(localDate: LocalDate): Int = localDateToString(localDate).replace("-", "").toInt

  def localDateToInt(yyyy: Int, mm: Int, dd: Int): Int = localDateToInt(LocalDate.of(yyyy, mm, dd))

  def localDateToString(localDate: LocalDate): String = localDate.format(dateFormatter)

  def localDateAsStringToInt(localDate: String): Int = localDate.replace("-", "").toInt

  def localDateAsIntToString(localDate: Int): String = {
    val localDateAsString = localDate.toString
    val yyyy = localDateAsString.substring(0, 4)
    val mm = localDateAsString.substring(4, 6)
    val dd = localDateAsString.substring(6, 8)
    LocalDate.of(yyyy.toInt, mm.toInt, dd.toInt).format(dateFormatter)
  }

  def currentTime: Int = localTimeToInt(LocalTime.now)

  def localTimeToInt(localTime: LocalTime): Int = localTimeToString(localTime).replace(":", "").toInt

  def localTimeToInt(hh: Int, mm: Int): Int = localTimeToInt(LocalTime.of(hh, mm))

  def localTimeToString(localTime: LocalTime): String = localTime.format(timeFormatter)

  def localTimeAsStringToInt(localTime: String): Int = localTime.replace(":", "").toInt

  def localTimeAsIntToString(localTime: Int): String = {
    val localTimeAsString = localTime.toString
    var hh = ""
    var mm = ""
    if (localTimeAsString.length == 3) {
      hh = localTimeAsString.substring(0, 1)
      mm = localTimeAsString.substring(1, 3)
    } else {
      hh = localTimeAsString.substring(0, 2)
      mm = localTimeAsString.substring(2, 4)
    }
    LocalTime.of(hh.toInt, mm.toInt).format(timeFormatter)
  }

  def nano: Int = Instant.now().getNano
}