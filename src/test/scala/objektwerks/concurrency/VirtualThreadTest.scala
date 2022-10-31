package objektwerks.concurrency

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer
import scala.math.*
import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Try, Using}

class VirtualThreadTest extends AnyFunSuite with Matchers:
  test("virtual threads") {
    val tasks = ArrayBuffer.empty[FibonacciTask]
    for(i <- 1 to 100) tasks += FibonacciTask(i)

    val result: Try[Long] = Using(Executors.newVirtualThreadPerTaskExecutor()) { executor =>
      val futures = executor.invokeAll(tasks.asJava)
      var sum = 0L
      futures.asScala.foreach { future => sum += future.get() }
      sum
    }

    result match
      case Success(sum) => assert(84580933396L == abs(sum))
      case Failure(error) => fail(error.getMessage())
  }