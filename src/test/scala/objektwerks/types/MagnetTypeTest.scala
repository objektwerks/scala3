package objektwerks.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration.Duration

sealed trait FutureMagnet:
  type Result

  def apply() : Result

object FutureMagnet:
  implicit def completeIntFuture(future: Future[Int]): FutureMagnet = new FutureMagnet:
    override type Result = Int

    override def apply(): Result = Await.result( future, Duration.Zero )

  implicit def completeStringFuture(future: Future[String]): FutureMagnet = new FutureMagnet:
    override type Result = String

    override def apply(): Result = Await.result( future, Duration.Zero )

def completeFuture(magnet: FutureMagnet):magnet.Result = magnet()

final class MagnetTypeTest extends AnyFunSuite with Matchers:
  test("pattern"):
    given ExecutionContext = ExecutionContext.global

    completeFuture( Future { 1 + 2 } ) shouldBe 3
    completeFuture( Future { "magnet test" } ) shouldBe "magnet test"