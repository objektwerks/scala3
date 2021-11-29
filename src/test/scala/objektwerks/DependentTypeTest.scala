package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DependentTypeTest extends AnyFunSuite with Matchers:
  trait Container:
    type Value
    val value: Value

  def extractValue(container: Container): container.Value = container.value
  
  val valueExtractor: (c: Container) => c.Value = extractValue

  def valueOf[T](t: T) = new Container {
    type Value = T
    val value: T = t
  }

  test("method") {
    extractValue( valueOf(1) ) shouldBe 1
    extractValue( valueOf("one") ) shouldBe "one"
  }

  test("function") {
    
  }