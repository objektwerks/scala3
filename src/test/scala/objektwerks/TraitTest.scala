package objektwerks

import munit._

trait Salutation(val addresee: String) {
  def address(message: String): String = s"$addresee, $message"
}

object Addresee {
  val sir = "Sir"
  val madam = "Madam"
}

object Message {
  val goodMorning = "I bid you good morning."
  val goodEvening = "I bid you good evening"
}

import Addresee._
case class MaleHonorific(message: String) extends Salutation(sir)
case class FemaleHonorific(message: String) extends Salutation(madam)

class TraitTest extends FunSuite {
  test("trait") {
    import Message._

    val maleHonorific = MaleHonorific(goodMorning)
    assert( maleHonorific.address(maleHonorific.message) == s"${maleHonorific.addresee}, ${maleHonorific.message}" )

    val femaleHonorific = FemaleHonorific(goodEvening)
    assert( femaleHonorific.address(femaleHonorific.message) == s"${femaleHonorific.addresee}, ${femaleHonorific.message}" )
  }
}