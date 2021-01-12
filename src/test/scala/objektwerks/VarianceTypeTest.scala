package classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Animal
class Cat extends Animal
class Dog extends Animal

class VarianceTypeTest extends AnyFunSuite with Matchers {
  test("invariant") {
    class Vet[T] {
      def heal(animal: T): T = animal
    }

    val vet = Vet[Animal]
    vet.heal( Cat() ).isInstanceOf[Cat] shouldBe true
    vet.heal( Dog() ).isInstanceOf[Dog] shouldBe true
  }

  test("covariant") {
    class Vet[+T] {
      def heal[S >: T](animal: S): S = animal
    }

    val vet = Vet[Animal]
    vet.heal( Cat() ).isInstanceOf[Cat] shouldBe true
    vet.heal( Dog() ).isInstanceOf[Dog] shouldBe true
  }

  test("contravariant") {
    class Vet[-T] {
      def heal[S <: T](animal: S): S = animal
    }

    val vet = Vet[Animal]
    vet.heal( Cat() ).isInstanceOf[Cat] shouldBe true
    vet.heal( Dog() ).isInstanceOf[Dog] shouldBe true
  }

  test("contravariant in, covariant out") {
    trait NotNullFilter[-V, +R] {
      def notNull(value: V): R
    }

    val notNullFilter = new NotNullFilter[String, Boolean] {
      override def notNull(v: String): Boolean = v != null
    }

    val values = List("a", "b", "c", null)
    val notNulls = values.filter( value => notNullFilter.notNull(value) )

    notNulls shouldEqual List("a", "b", "c")
  }
}