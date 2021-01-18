package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

class ContextFunctionTest extends AnyFunSuite with Matchers:
  type Executable[T] = ExecutionContext ?=> T

  given ec: ExecutionContext = ExecutionContext.global

  def square(n: Int): Executable[Future[Int]] = Future { n * n }

  test("?=>") {
    square(2).foreach( result => result shouldBe 4 )
  }