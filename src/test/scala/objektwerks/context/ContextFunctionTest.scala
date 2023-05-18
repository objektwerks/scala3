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

  final case class AuthToken(id: String = UUID.randomUUID.toString) {
    def isValid = id.nonEmpty
  }

  type AuthTokenContext[T] = AuthToken ?=> T

  def login(pin: String): AuthTokenContext[AuthToken] = AuthToken()

  def handle(message: String)(using authToken: AuthTokenContext[AuthToken]): Boolean = message.nonEmpty

  test("auth token context") {
    given AuthTokenContext[AuthToken] = login(pin = "1a2b3c4")
    val result = handle(message = "test")
    result shouldBe true
  }