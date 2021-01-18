package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Canine
final class Dog extends Canine
final class Wolf extends Canine

class TypeVarianceTest extends AnyFunSuite with Matchers:
  test("invariant") {
    class Vet[T]:
      def heal[T](canine: T): T = canine

    val vet = Vet[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: Vet[Dog] = Vet[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: Vet[Wolf] = Vet[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("covariant") {
    class Vet[+T]:
      def heal[S >: T](canine: S): S = canine

    val vet = Vet[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: Vet[Dog] = Vet[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: Vet[Wolf] = Vet[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("contravariant") {
    class Vet[-T]:
      def heal[S <: T](canine: S): S = canine

    val vet = Vet[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: Vet[Dog] = Vet[Canine]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: Vet[Wolf] = Vet[Canine]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("contravariant in, covariant out") {
    trait Function[-V, +R]:
      def apply(value: V): R

    val function = new Function[String, Option[Int]]:
      def apply(value: String): Option[Int] = value.toIntOption

    val values = List("1", "2", "3", "four")
    values.flatMap(value => function(value)) shouldEqual List(1, 2, 3)
    values.flatMap(value => function(value)).sum shouldEqual 6
  }