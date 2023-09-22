package objektwerks.equal

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.language.strictEquality

final class CanEqualTest extends AnyFunSuite with Matchers:
  sealed trait Book:
      def author: String
      def title: String
      def year: Int

  final case class PrintedBook(author: String, title: String, year: Int, pages: Int) extends Book
  final case class AudioBook(author: String, title: String, year: Int, minutes: Int) extends Book

  given CanEqual[PrintedBook, PrintedBook] = CanEqual.derived
  given CanEqual[AudioBook, AudioBook] = CanEqual.derived

  test("can equal") {
    val pb1 = PrintedBook("Beer Bible", "Fred Flintstone", 2000, 425)
    val pb2 = PrintedBook("Beer Bible", "Fred Flintstone", 2000, 425)
    val pb3 = PrintedBook("IPA Recipes", "Barney Rebel", 2001, 325)
    // See below. val ab1 = AudioBook("IPA Recipes", "Barney Rebel", 2001, 650)

    (pb1 == pb2) shouldBe true
    (pb1 == pb3) shouldBe false
    // Can't compare different book types: (pb3 == ab1) shouldBe false
  }