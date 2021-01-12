package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class First {
  class Second
}

class DependentTypeTest extends AnyFunSuite with Matchers {
  test("dependent type") {
    val firstDependent1 = First()
    val firstToSecondDependentPath1 = firstDependent1.Second()

    val firstDependent2 = First()
    val firstToSecondDependentPath2 = firstDependent2.Second()

    firstToSecondDependentPath1 should not equal firstToSecondDependentPath2
  }
}