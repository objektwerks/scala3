package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Canine
class Dog extends Canine
class Wolf extends Canine

class VarianceTypeTest extends AnyFunSuite with Matchers {
  test("invariant") {
    class Vet[T] {
      def heal[T](canine: T): T = canine
    }

    val vet = Vet[Canine]
    vet.heal( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: Vet[Dog] = Vet[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: Vet[Wolf] = Vet[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("covariant") {
    class Vet[+T] {
      def heal[S >: T](canine: S): S = canine
    }

    val vet = Vet[Canine]
    vet.heal( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: Vet[Dog] = Vet[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: Vet[Wolf] = Vet[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("contravariant") {
    class Vet[-T] {
      def heal[S <: T](canine: S): S = canine
    }

    val vet = Vet[Canine]
    vet.heal( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: Vet[Dog] = Vet[Canine]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: Vet[Wolf] = Vet[Canine]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
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