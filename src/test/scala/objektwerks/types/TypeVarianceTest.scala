package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class TypeVarianceTest extends AnyFunSuite with Matchers:
  sealed trait Canine
  final class Dog extends Canine
  final class Wolf extends Canine

  final class VetInvariant[T]:
    def heal[T](canine: T): T = canine

  final class VetCovariant[+T]:
    def heal[S >: T](canine: S): S = canine

  final class VetContravariant[-T]:
    def heal[S <: T](canine: S): S = canine

  trait Function[-V, +R]:
    def apply(value: V): R

  test("invariant"):
    val vet = VetInvariant[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: VetInvariant[Dog] = VetInvariant[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: VetInvariant[Wolf] = VetInvariant[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true

  test("covariant"):
    val vet = VetCovariant[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: VetCovariant[Dog] = VetCovariant[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: VetCovariant[Wolf] = VetCovariant[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true

  test("contravariant"):
    val vet = VetContravariant[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: VetContravariant[Dog] = VetContravariant[Canine]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: VetContravariant[Wolf] = VetContravariant[Canine]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true

  test("contravariant in, covariant out"):
    val toIntOption = new Function[String, Option[Int]]:
      def apply(value: String): Option[Int] = value.toIntOption

    val values = List("1", "2", "3", "four")
    values.flatMap(value => toIntOption(value)) shouldEqual List(1, 2, 3)
    values.flatMap(value => toIntOption(value)).sum shouldEqual 6