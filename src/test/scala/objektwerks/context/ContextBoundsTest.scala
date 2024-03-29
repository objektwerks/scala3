package objektwerks.context

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class ContextBoundsTest extends AnyFunSuite with Matchers:
  def maximum[A: Ordering](a: A, b: A): A =
    val ordering = summon[Ordering[A]]
    ordering.max(a, b)

  test("context bounds"):
    maximum( 1, 2 ) shouldBe 2