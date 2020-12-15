package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

type Executable[T] = ExecutionContext ?=> T

def square(n: Int): Executable[Future[Int]] = Future { n * n }

class ContextFunctionTest extends AnyFunSuite with Matchers {
  test("?=>") {
    given ec as ExecutionContext = ExecutionContext.global
    square(2).foreach( result => result shouldBe 4 )
  }
}