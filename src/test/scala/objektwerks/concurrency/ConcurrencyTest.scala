package objektwerks.concurrency

import java.util.concurrent.{Callable, Executors, Future}
import jdk.incubator.concurrent.StructuredTaskScope

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.tailrec
import scala.io.{Codec, Source}
import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Try, Using}

object FileLineCountTask:
  def defaultTasks: List[FileLineCountTask] = List( FileLineCountTask("./data/data.a.csv"), FileLineCountTask("./data/data.b.csv") )

final class FileLineCountTask(file: String) extends Callable[Int]:
  def fileLineCount(file: String): Int = 
    Using( Source.fromFile(file, Codec.UTF8.name) ) { source =>
      source.getLines().length
    }.get

  def call(): Int = fileLineCount(file)

/**
  * Virtual Threads: https://openjdk.org/jeps/425
  * Structured Concurrency: https://openjdk.org/jeps/428
  */
class ConcurrencyTest extends AnyFunSuite:
  test("virtual threads") {
    Using(Executors.newVirtualThreadPerTaskExecutor()) { executor =>
      val futures = executor.invokeAll(FileLineCountTask.defaultTasks.asJava)
      futures.asScala.map(future => future.get()).sum
    }.fold( error => fail(error.getMessage()), lines => assert(lines == 540959) )
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
      case Success(lines) => assert(lines == 540959)
      case Failure(error) => fail(error.getMessage())
  }