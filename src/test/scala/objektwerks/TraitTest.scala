package objektwerks

import munit._

trait Salutation(addresee: String) {
  def address(message: String): String = s"$addresee, $message"
}

case class MaleHonorific(addresee: String, message: String) extends Salutation(addresee)
case class FemaleHonorific(addresee: String, message: String) extends Salutation(addresee)

class TraitTest extends FunSuite {
  test("trait") {
    val maleHonorific = MaleHonorific("Sir", "I bid you good morning.")
    assert( maleHonorific.address(maleHonorific.message) == s"${maleHonorific.addresee}, ${maleHonorific.message}" )

    val femaleHonorific = FemaleHonorific("Madam", "I bid you good evening.")
    assert( femaleHonorific.address(femaleHonorific.message) == s"${femaleHonorific.addresee}, ${femaleHonorific.message}" )
  }
}