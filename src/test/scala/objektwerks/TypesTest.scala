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

trait NotNullFilter[-V, +R] {
  def notNull(value: V): R
}

sealed trait Animal
class Dog extends Animal

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

  test("invariant") {
    class Vet[T](val animal: T)
    val dog: Dog = Dog()
    val vet: Vet[Animal] = Vet[Animal](dog)
    vet.animal.isInstanceOf[Animal] shouldBe true
    vet.animal.isInstanceOf[Dog] shouldBe true
  }

  test("covariant") {
    class Vet[+T](val animal: T)
    val dog: Dog = Dog()
    val vet: Vet[Animal] = Vet[Dog](dog)
    vet.animal.isInstanceOf[Animal] shouldBe true
    vet.animal.isInstanceOf[Dog] shouldBe true
  }

  test("contravariant") {
    class Vet[-T <: Animal](val animal: Animal)
    val dog = Dog()
    val vet: Vet[Dog] = Vet[Animal](dog)
    vet.animal.isInstanceOf[Animal] shouldBe true
    vet.animal.isInstanceOf[Dog] shouldBe true
  }

  test("contravariant in, covariant out") {
    val filter = new NotNullFilter[String, Boolean] {
      override def notNull(v: String): Boolean = v != null
    }

    val values = List("a", "b", "c", null)
    val notNulls = values.filter(v => filter.notNull(v))

    notNulls shouldEqual List("a", "b", "c")
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
    val users =  Map[User, Age]("john" -> 21, "jane" -> 19)

    users("john") shouldEqual 21
    users("jane") shouldEqual 19
  }

  test("path dependent types") {
    val firstDependent1 = First()
    val firstToSecondDependentPath1 = firstDependent1.Second()

    val firstDependent2 = First()
    val firstToSecondDependentPath2 = firstDependent2.Second()

    firstToSecondDependentPath1 should not equal firstToSecondDependentPath2
  }
}