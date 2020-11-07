package objektwerks

import munit._

sealed trait Salutation(val addresee: Addresee) {
  def address(message: Message): String = s"$addresee, $message"
}

enum Addresee(value: String) {
  case sir extends Addresee("Sir")
  case madam extends Addresee("Madam")
}

enum Message(value: String) {
  case goodMorning extends Message("I bid you good morning.")
  case goodEvening extends Message("I bid you good evening")
}

import Addresee._
final case class MaleHonorific(message: Message) extends Salutation(sir)
final case class FemaleHonorific(message: Message) extends Salutation(madam)

class TraitTest extends FunSuite {
  test("trait") {
    import Message._

    val maleHonorific = MaleHonorific(goodMorning)
    assert( maleHonorific.address(maleHonorific.message) == s"${maleHonorific.addresee}, ${maleHonorific.message}" )

    val femaleHonorific = FemaleHonorific(goodEvening)
    assert( femaleHonorific.address(femaleHonorific.message) == s"${femaleHonorific.addresee}, ${femaleHonorific.message}" )
  }
}