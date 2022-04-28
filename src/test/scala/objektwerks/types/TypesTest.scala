package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TypesTest extends AnyFunSuite with Matchers:
  test("alias") {
    type User = String
    type Age = Int
    val users =  Map[User, Age]("john" -> 21, "jane" -> 19)

    users("john") shouldEqual 21
    users("jane") shouldEqual 19
  }

  test("literal") {
    val n = 2
    val two: 2 = 2
    val pi: 3.14 = 3.14

    def bounce(n: Int): Int = n
    def bounceStrict(two: 2): Int = n

    assert( bounce(n) == n )
    assert( bounce(two) == two )
    // assert( bounceStrict(n) == n ) Found: (n : Int) Required: (2 : Int)
    assert( bounceStrict(two) == two )
    assert( 3.14 == pi )
  }