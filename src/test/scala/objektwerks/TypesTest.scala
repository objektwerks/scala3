package classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

// Variance
sealed trait Relative
class Parent extends Relative
class Child extends Parent

class Covariant[+R](val relative: R)
class Contravariant[-R, +S](val relative: S)
class Invariant[R](val relative: R)

trait PositiveFilter[-A, +B] { 
  def isPositive(n: Int): Boolean 
}

// Bounds
object UpperBounds { 
  def apply[U <: AnyVal](n: U): U = identity(n) 
}
object LowerBounds { 
  def apply[L >: AnyVal](n: L): L = identity(n) 
}

// Compound Type
trait Init { 
  def init: Boolean = true 
}
trait Run extends Init { 
  def run: Boolean = init
}
class Runnable extends Run { 
  def isRunning: Boolean = run 
}
trait Emotion { 
  def isEmoting: Boolean = true 
}
trait Speach { 
  def isSpeaking: Boolean = true 
}
class Robot extends Runnable with Emotion with Speach

// Self Type
trait Speaking { 
  def speaking: String 
}
trait Hello extends Speaking { 
  override def speaking = "hello" 
}
trait Goodbye extends Speaking { 
  override def speaking = "goodbye" 
}
class Speaker {
  self: Speaking =>
  def speak: String = speaking
}

// Path Dependent Type
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
    val positives = numbers.filter(n => filter.isPositive(n))

    positives shouldEqual List(1, 2, 3)
  }

  test("bounds") {
    val upperBounds: Int = UpperBounds(3)
    upperBounds shouldEqual 3

    val lowerBounds: Any = LowerBounds(3)
    lowerBounds shouldEqual 3
  }

  test("compound type") {
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
    val helloSpeaker = new Speaker() with Hello
    helloSpeaker.speak shouldEqual "hello"

    val goodbyeSpeaker = new Speaker() with Goodbye
    goodbyeSpeaker.speak shouldEqual "goodbye"
  }

  test("path dependent types") {
    val firstDependent1 = First()
    val firstToSecondDependentPath1 = firstDependent1.Second()

    val firstDependent2 = First()
    val firstToSecondDependentPath2 = firstDependent2.Second()

    firstToSecondDependentPath1 should not equal firstToSecondDependentPath2
  }
}