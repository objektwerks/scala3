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

  final case class Pin(value: String)
  final case class Token(value: String = UUID.randomUUID.toString)
  final case class AuthToken(pin: Pin, token: Token)

  def login: Pin ?=> AuthToken = AuthToken( summon[Pin], Token() )

  def handle(message: String)(using authToken: AuthToken): Boolean =
    println(summon[AuthToken])
    message.nonEmpty

  test("auth token context") {
    given Pin = Pin("1a2b3c4")
    given AuthToken = login
    val result = handle(message = "test")
    result shouldBe true
  }