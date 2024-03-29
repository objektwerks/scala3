package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

final class DependentTypeTest extends AnyFunSuite with Matchers:
  trait Container:
    type Value
    val value: Value

  def unbox(container: Container): container.Value = container.value  // dependent method, on container
  
  def unboxer(container: Container): container.Value = unbox(container)  // dependent function, on unbox

  def box[T](t: T): Container = new Container:
    override type Value = T
    val value: T = t

  test("method"):
    unbox( box(1) ) shouldBe 1
    unbox( box("one") ) shouldBe "one"

  test("function"):
    unboxer( box(1) ) shouldBe 1
    unboxer( box("one") ) shouldBe "one"