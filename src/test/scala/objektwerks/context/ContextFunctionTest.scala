package objektwerks.context

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{ExecutionContext, Future}

/**
  * See: https://blog.softwaremill.com/context-is-king-20f533474cb3
  */
class ContextFunctionTest extends AnyFunSuite with Matchers:
  type Executable[T] = ExecutionContext ?=> Future[T]

  given ec: ExecutionContext = ExecutionContext.global

  def square(n: Int): Executable[Int] = Future { n * n }

  test("?=>") {
    square(2).foreach( result => result shouldBe 4 )
  }