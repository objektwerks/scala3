package objektwerks.concurrency

import java.util.concurrent.{Callable, Executors, Future}
import jdk.incubator.concurrent.StructuredTaskScope

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.*
import scala.math.*
import scala.util.{Failure, Success, Try, Using}

final class FactorialTask(n: Int) extends Callable[Long]:
  @tailrec final def factorial(n: Int, acc: Int = 1): Int = n match
    case i if i < 1 => acc
    case _ => factorial(n - 1, acc * n)

  def call(): Long = factorial(n, 1)

final class FibonacciTask(n: Int) extends Callable[Long]:
  def fibonacci(n: Int, a: Int, b: Int): Long =
    if (n == 0) then a
    else fibonacci(n -1, b, a + b)

  def call(): Long = fibonacci(n, 0, 1)

class ConcurrencyTest extends AnyFunSuite with Matchers:
  test("unstructured") {
    val tasks = ArrayBuffer.empty[FibonacciTask]
    for(i <- 1 to 100) tasks += FibonacciTask(i)

    val result: Try[Long] = Using(Executors.newVirtualThreadPerTaskExecutor()) { executor =>
      val futures = executor.invokeAll(tasks.asJava)
      futures.asScala.map(_.get()).sum
    }

    result match
      case Success(sum) => assert(7144671097L == abs(sum))
      case Failure(error) => fail(error.getMessage())
  }

  test("structured") {
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