package objektwerks

import munit._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

type Executable[T] = ExecutionContext ?=> T

def square(n: Int): Executable[Future[Int]] = Future { n * n }

class ContextFunctionTest extends FunSuite {
  test("?=>") {
    given ec as ExecutionContext = ExecutionContext.global
    square(2).foreach( result => assert( result == 4 ) )
  }
}