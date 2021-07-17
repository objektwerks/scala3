package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

sealed trait Canine
final class Dog extends Canine
final class Wolf extends Canine

class VetA[T]:
  def heal[T](canine: T): T = canine

class VetB[+T]:
  def heal[S >: T](canine: S): S = canine

class VetC[-T]:
  def heal[S <: T](canine: S): S = canine

trait Function[-V, +R]:
  def apply(value: V): R

class TypeVarianceTest extends AnyFunSuite with Matchers:      
  test("invariant") {
    val vet = VetA[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: VetA[Dog] = VetA[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: VetA[Wolf] = VetA[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("covariant") {
    val vet = VetB[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: VetB[Dog] = VetB[Dog]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: VetB[Wolf] = VetB[Wolf]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("contravariant") {
    val vet = VetC[Canine]
    vet.heal[Canine]( Dog() ).isInstanceOf[Dog] shouldBe true
    vet.heal[Canine]( Wolf() ).isInstanceOf[Wolf] shouldBe true

    val dogVet: VetC[Dog] = VetC[Canine]
    dogVet.heal[Dog]( Dog() ).isInstanceOf[Dog] shouldBe true

    val wolfVet: VetC[Wolf] = VetC[Canine]
    wolfVet.heal[Wolf]( Wolf() ).isInstanceOf[Wolf] shouldBe true
  }

  test("contravariant in, covariant out") {
    val function = new Function[String, Option[Int]]:
      def apply(value: String): Option[Int] = value.toIntOption

    val values = List("1", "2", "3", "four")
    values.flatMap(value => function(value)) shouldEqual List(1, 2, 3)
    values.flatMap(value => function(value)).sum shouldEqual 6
  }