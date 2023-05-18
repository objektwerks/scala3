package objektwerks.context

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{ExecutionContext, Future}
import java.util.UUID

/**
  * See: https://blog.softwaremill.com/context-is-king-20f533474cb3
  */
class ContextFunctionTest extends AnyFunSuite with Matchers:
  given ec: ExecutionContext = ExecutionContext.global

  type Executable[T] = ExecutionContext ?=> Future[T]

  def square(n: Int): Executable[Int] = Future { n * n }

  test("executable context") {
    square(2).foreach( result => result shouldBe 4 )
  }

  final case class Authorization(id: String = UUID.randomUUID.toString)

  type AuthorizationContext[T] = Authorization ?=> T

  def login(pin: String): AuthorizationContext[Authorization] = Authorization()

  test("authorization context") {

  }