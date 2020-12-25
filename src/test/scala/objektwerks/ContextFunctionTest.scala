package objektwerks

import munit._

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

type Executable[T] = ExecutionContext ?=> T
given ec: ExecutionContext = ExecutionContext.global

def square(n: Int): Executable[Future[Int]] = Future { n * n }

class ContextFunctionTest extends FunSuite {
  test("?=>") {
    square(2).foreach( result => assert( result == 4 ) )
  }
}