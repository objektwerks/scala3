package objektwerks.concurrency

import java.time.Instant
import java.util.concurrent.{ Callable, Executors, Future }
import jdk.incubator.concurrent.StructuredTaskScope

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.tailrec
import scala.io.{ Codec, Source }
import scala.jdk.CollectionConverters.*
import scala.util.{ Failure, Success, Try, Using }

object FileLineCountTask:
  def tasks: List[FileLineCountTask] = List( FileLineCountTask("./data/data.a.csv"), FileLineCountTask("./data/data.b.csv") )

final class FileLineCountTask(file: String) extends Callable[Int]:
  def fileLineCount(file: String): Int = 
    Using( Source.fromFile(file, Codec.UTF8.name) ) { source =>
      source.getLines().length
    }.get

  def call(): Int = fileLineCount(file)

/**
  * Virtual Threads: openjdk.org/jeps/425
  * Structured Concurrency: openjdk.org/jeps/428
  * Article: www.marcobehler.com/guides/java-project-loom
  * Using: scala-lang.org/api/3.x/scala/util/Using$.html# | www.baeldung.com/scala/try-with-resources
  */
class ConcurrencyTest extends AnyFunSuite:
  test("virtual threads") {
    Using( Executors.newVirtualThreadPerTaskExecutor() ) { executor =>
      val futures = executor.invokeAll( FileLineCountTask.tasks.asJava )
      futures.asScala.map( future => future.get() ).sum
    }.fold( error => fail(error.getMessage()), lines => assert(lines == 540_959) )
  }

  test("structured concurrency") {
    val lines = Using( StructuredTaskScope.ShutdownOnFailure() ) { scope =>
      val alines = scope.fork( () => FileLineCountTask("./data/data.a.csv").call() )
      val blines = scope.fork( () => FileLineCountTask("./data/data.b.csv").call() )
      scope.join()
      scope.throwIfFailed()
      alines.resultNow() + blines.resultNow()
    }
    lines match
      case Success(count) => assert(count == 540_959)
      case Failure(error) => fail(error.getMessage())
  }

  test("structured concurrency x") {
    Using( StructuredTaskScope.ShutdownOnFailure() ) { scope =>
      val futures = FileLineCountTask.tasks.map( task => scope.fork( () => task.call() ) )
      scope.joinUntil( Instant.now().plusMillis(3000) )
      scope.throwIfFailed()
      futures.map( future => future.resultNow() ).sum
    }.fold( error => fail(error.getMessage()), lines => assert(lines == 540_959) )
  }