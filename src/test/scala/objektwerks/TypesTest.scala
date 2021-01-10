package classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

// Variance
trait Relative
class Parent extends Relative
class Child extends Parent
class Covariant[+RR](val relative: RR)
class Contravariant[-RR, +SS](val relative: SS)
class Invariant[RR](val relative: RR)
trait PositiveFilter[-AA, +BB] { def isPositive(n: Int): Boolean }

// Bounds
object UpperBounds { def apply[UB <: AnyVal](n: UB): UB = identity(n) }
object LowerBounds { def apply[LB >: AnyVal](n: LB): LB = identity(n) }

// Compound Types
trait Init { def init: Boolean = true }
trait Run extends Init { def run: Boolean = init }
class Runnable extends Run {
  def isRunning: Boolean = run
}
trait Emotion { def isEmoting: Boolean = true }
trait Speach { def isSpeaking: Boolean = true }
class Robot extends Runnable with Emotion with Speach

// Self Type
trait Greeting { def greeting: String }
trait Hello extends Greeting { override def greeting = "hello" }
trait Goodbye extends Greeting { override def greeting = "goodbye" }
class Speaker {
  self: Greeting =>
  def greet: String = greeting
}

// Path Dependent Types
class First {
  class Second
}

class TypesTest extends AnyFunSuite with Matchers {
  test("variance") {
    val covariant: Covariant[Parent] = Covariant[Child](Child())
    val contravariant: Contravariant[Child, Parent] = Contravariant[Child, Parent](Parent())
    val invariant: Invariant[Child] = Invariant[Child](Child())
    covariant.relative.isInstanceOf[Child] shouldBe true
    contravariant.relative.isInstanceOf[Parent] shouldBe true
    invariant.relative.isInstanceOf[Child] shouldBe true
  }

  test("contravariant in, covariant out") {
    val filter = new PositiveFilter[Int, Boolean] {
      override def isPositive(n: Int): Boolean = n > 0
    }
    val numbers = List(-3, -2, -1, 0, 1, 2, 3)
    val positives: List[Int] = numbers.filter(n => filter.isPositive(n))
    positives shouldEqual List(1, 2, 3)
  }

  test("bounds") {
    val upperBounds: Int = UpperBounds(3)
    upperBounds shouldEqual 3

    val lowerBounds: Any = LowerBounds(3)
    lowerBounds shouldEqual 3
  }

  test("compound types") {
    val robot = Robot()
    robot.isRunning shouldBe true
    robot.isEmoting shouldBe true
    robot.isSpeaking shouldBe true
  }

  test("type alias") {
    type User = String
    type Age = Int
    val users:  Map[User, Age] =  Map("john" -> 21, "jane" -> 19)
    users("john") shouldEqual 21
    users("jane") shouldEqual 19
  }

  test("self type") {
    val hello = new Speaker() with Hello
    hello.greet shouldEqual "hello"

    val goodbye = new Speaker() with Goodbye
    goodbye.greet shouldEqual "goodbye"
  }

  test("path dependent types") {
    val first1 = First()
    val path1 = first1.Second()
    val first2 = First()
    val path2 = first2.Second()
    path1 should not equal path2
  }
}