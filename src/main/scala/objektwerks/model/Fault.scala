package objektwerks.model

final case class Fault(dateOf: Int,
                       timeOf: Int,
                       nanoOf: Int,
                       code: Int,
                       cause: String)

object Fault {
  val serviceFailure = 500

  def apply(code: Int, cause: String): Fault = Fault(
    dateOf = DateTime.currentDate,
    timeOf = DateTime.currentTime,
    nanoOf = DateTime.nano,
    code = code,
    cause = cause
  )

  def apply(cause: String): Fault = apply(serviceFailure, cause)
}