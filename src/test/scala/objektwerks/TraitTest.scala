package objektwerks

import munit._

trait Salutation(val addresee: String) {
  def address(message: String): String = s"$addresee, $message"
}

object Addresee {
  val sir = "Sir"
  val madam = "Madam"
}

import Addresee._
case class MaleHonorific(message: String) extends Salutation(sir)
case class FemaleHonorific(message: String) extends Salutation(madam)

class TraitTest extends FunSuite {
  test("trait") {
    val maleHonorific = MaleHonorific("I bid you good morning.")
    assert( maleHonorific.address(maleHonorific.message) == s"${maleHonorific.addresee}, ${maleHonorific.message}" )

    val femaleHonorific = FemaleHonorific("I bid you good evening.")
    assert( femaleHonorific.address(femaleHonorific.message) == s"${femaleHonorific.addresee}, ${femaleHonorific.message}" )
  }
}