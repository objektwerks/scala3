package objektwerks.concurrency

import java.util.concurrent.{Executors, Future}

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.*
import scala.math.*
import scala.util.{Failure, Success, Try, Using}

class VirtualThreadTest extends AnyFunSuite with Matchers:
  test("virtual threads") {
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