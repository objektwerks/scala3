package classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Animal
class Dog extends Animal

trait NotNullFilter[-V, +R] {
  def notNull(value: V): R
}

object UpperBounds { 
  def apply[U <: AnyVal](n: U): U = identity(n) 
}
object LowerBounds { 
  def apply[L >: AnyVal](n: L): L = identity(n) 
}

class VarianceTypeTest extends AnyFunSuite with Matchers {
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
}