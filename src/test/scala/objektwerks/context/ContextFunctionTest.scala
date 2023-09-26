package objektwerks.context

import java.util.UUID

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{ExecutionContext, Future}
import scala.annotation.tailrec

/**
  * See
  * 1. https://blog.softwaremill.com/context-is-king-20f533474cb3
  * 2. https://dev.to/markehammons/the-wonder-of-context-functions-2200
  */
final class ContextFunctionTest extends AnyFunSuite with Matchers:
  given ExecutionContext = ExecutionContext.global

  type Executable[T] = ExecutionContext ?=> Future[T]

  def calc(n: Int): Executable[Int] =
    @tailrec
    def factorial(n: Int, acc: Int = 1): Int = n match
      case i if i < 1 => acc
      case _ => factorial(n - 1, acc * n)

    Future { factorial(n) }

  test("executable"):
    calc(3).foreach( result => result shouldBe 6 )

  final case class Pin(value: String)
  final case class Token(value: String = UUID.randomUUID.toString)
  final case class AuthToken(pin: Pin, token: Token = Token()):
    def isValid: Boolean = pin.value.nonEmpty && token.value.nonEmpty

  def login: Pin ?=> AuthToken = AuthToken( summon[Pin] )

  def handle(message: String)(using authToken: AuthToken): Boolean =
    summon[AuthToken].isValid && message.nonEmpty

  test("auth token"):
    given Pin = Pin("1a2b3c4")
    given AuthToken = login
    val message = "test"
    handle(message) shouldBe true