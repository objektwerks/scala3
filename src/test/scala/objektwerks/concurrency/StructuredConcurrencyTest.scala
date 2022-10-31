package objektwerks.concurrency

import java.util.concurrent.Future
import jdk.incubator.concurrent.StructuredTaskScope

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.math.*
import scala.util.{Failure, Success, Try, Using}

class StructuredConcurrencyTest extends AnyFunSuite with Matchers:
  test("structured concurrency") {
    val result: Try[Long] = Using (new StructuredTaskScope.ShutdownOnFailure()) { scope =>
      val factorial = scope.fork(() => new FactorialTask(50).call())
      val fibonacci = scope.fork(() => new FibonacciTask(50).call())

      scope.join();
      scope.throwIfFailed();

      factorial.get() + fibonacci.get()
    }

    result match
      case Success(sum) => assert(298632863L == abs(sum))
      case Failure(error) => fail(error.getMessage())
  }