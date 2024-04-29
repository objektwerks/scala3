package objektwerks.control

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.boundary
import scala.util.boundary.break

class BoundaryBreakTest extends AnyFunSuite with Matchers:
  def sumOfRoots(numbers: List[Double]): Option[Double] =
    boundary:
      val roots = numbers.map: number => 
        if number >= 0 then Math.sqrt(number)
        else break(None)
      Some(roots.sum.round.toDouble)

  test("boundary > break"):
    sumOfRoots( List(4.0, 8.0) ) shouldBe Some(5.0)
    sumOfRoots( List(-4.0, -8.0) ) shouldBe None