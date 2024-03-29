package objektwerks.control

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.boundary
import scala.util.boundary.break

class BoundaryBreakTest extends AnyFunSuite with Matchers:
  def sumOfRoots(numbers: List[Long]): Option[Long] =
    boundary:
      val roots = numbers.map: number => 
        if number >= 0 then Math.sqrt(number)
        else break(None)
      Some(roots.sum.round)

  test("boundary > break"):
    sumOfRoots( List(4, 8) ) shouldBe Some(5)
    sumOfRoots( List(-4, -8) ) shouldBe None