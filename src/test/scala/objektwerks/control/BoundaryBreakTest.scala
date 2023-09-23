package objektwerks.control

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import util.boundary, boundary.break

class BoundaryBreakTest extends AnyFunSuite with Matchers:
  def sumOfRoots(ns: List[Long]): Option[Long] =
    boundary:
      val roots = ns.map: n => 
        if n >= 0 then Math.sqrt(n)
        else break(None)
      Some(roots.sum.round)

  test("boundary > break"):
    sumOfRoots( List(4, 8) ) shouldBe Some(5)
    sumOfRoots( List(-4, -8) ) shouldBe None