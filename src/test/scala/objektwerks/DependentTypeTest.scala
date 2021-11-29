package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DependentTypeTest extends AnyFunSuite with Matchers:
  trait Container:
    type Value
    val value: Value

  def deriveValue(container: Container): container.Value = container.value
  
  val extractor: (c: Container) => c.Value = deriveValue

  test("method") {
    def valueOf[T](t: T) = new Container {
      type Value = T
      val value: T = t
    }

    val intValue = valueOf(1)
    val stringValue = valueOf("one")

    deriveValue(intValue) shouldBe 1
    deriveValue(stringValue) shouldBe "one"
  }

  test("function") {
    
  }