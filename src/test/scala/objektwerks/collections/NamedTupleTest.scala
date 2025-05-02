package objektwerks.collections

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class TupleTest extends AnyFunSuite with Matchers:
  test("named"):
    "named".nonEmpty shouldBe true
