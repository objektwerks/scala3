package objektwerks.concurrency

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.*
import scala.util.Using

class VirtualThreadTest extends AnyFunSuite with Matchers:
  test("virtual threads") {
    val tasks = ArrayBuffer.empty[FibonacciTask]
    for(i <- 1 to 100) tasks += FibonacciTask(i)

    var sum = 0L
    Using.resource(Executors.newVirtualThreadPerTaskExecutor()) { executor =>
      val futures = executor.invokeAll(tasks.asJava)
      futures.asScala.foreach { future => sum += future.get() }
    }

    System.out.println("sum: " + Math.abs(sum));
    assert(84580933396L == Math.abs(sum));
  }