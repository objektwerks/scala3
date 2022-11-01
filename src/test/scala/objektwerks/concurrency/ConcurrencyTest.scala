package objektwerks.concurrency

import java.util.concurrent.{Callable, Executors, Future}
import jdk.incubator.concurrent.StructuredTaskScope

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer
import scala.io.{Codec, Source}
import scala.jdk.CollectionConverters.*
import scala.math.*
import scala.util.{Failure, Success, Try, Using}

final class FileLineCountTask(file: String) extends Callable[Int]:
  def fileLineCount(file: String): Int = 
    Using( Source.fromFile(file, Codec.UTF8.name) ) { source =>
      source.getLines().length
    }.get

  def call(): Int = fileLineCount(file)

/**
  * <a href="https://openjdk.org/jeps/425">Virtual Threads</a>
  * <a href="https://openjdk.org/jeps/428">Structured Concurrency</a>
  */
class ConcurrencyTest extends AnyFunSuite with Matchers:
  test("virtual threads") {
    val tasks = ArrayBuffer.empty[FileLineCountTask]
    tasks += FileLineCountTask("./data/data.a.csv")
    tasks += FileLineCountTask("./data/data.b.csv")

    val result: Try[Long] = Using(Executors.newVirtualThreadPerTaskExecutor()) { executor =>
      val futures = executor.invokeAll(tasks.asJava)
      futures.asScala.map(future => future.get()).sum
    }

    result match
      case Success(sum) => assert(540959 == abs(sum))
      case Failure(error) => fail(error.getMessage())
  }

  test("structured concurrency") {
    val result: Try[Long] = Using (new StructuredTaskScope.ShutdownOnFailure()) { scope =>
      val factorial = scope.fork(() => new FileLineCountTask("./data/data.a.csv").call())
      val fibonacci = scope.fork(() => new FileLineCountTask("./data/data.b.csv").call())

      scope.join();
      scope.throwIfFailed();

      factorial.get() + fibonacci.get()
    }

    result match
      case Success(sum) => assert(540959 == abs(sum))
      case Failure(error) => fail(error.getMessage())
  }