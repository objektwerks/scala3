package objektwerks.concurrency

import java.time.Instant
import java.util.concurrent.{ Callable, Executors, Future }
import jdk.incubator.concurrent.StructuredTaskScope

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.tailrec
import scala.io.{ Codec, Source }
import scala.jdk.CollectionConverters.*
import scala.util.{ Failure, Success, Try, Using }

class FileLineCountTask(file: String) extends Callable[Int]:
  def call(): Int = Using( Source.fromFile(file, Codec.UTF8.name) ) { source => source.getLines().length }.get

/**
  * Virtual Threads: openjdk.org/jeps/425
  * Structured Concurrency: openjdk.org/jeps/428
  * Loom: www.marcobehler.com/guides/java-project-loom
  * Using: scala-lang.org/api/3.x/scala/util/Using$.html# | www.baeldung.com/scala/try-with-resources
  */
class ConcurrencyTest extends AnyFunSuite:
  val tasks = List( FileLineCountTask("./data/data.a.csv"), FileLineCountTask("./data/data.b.csv") )
  val expectedLineCount = 540_959

  test("virtual threads") {
    Using( Executors.newVirtualThreadPerTaskExecutor() ) { executor =>
      val futures = executor.invokeAll( tasks.asJava )
      futures.asScala.map( future => future.get() ).sum
    }.fold( error => fail(error.getMessage()), lines => assert(lines == expectedLineCount) )
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
      case Success(count) => assert(count == expectedLineCount)
      case Failure(error) => fail(error.getMessage())
  }

  test("structured concurrency x") {
    Using( StructuredTaskScope.ShutdownOnFailure() ) { scope =>
      val futures = tasks.map( task => scope.fork( () => task.call() ) )
      scope.joinUntil( Instant.now().plusMillis(3000) )
      scope.throwIfFailed()
      futures.map( future => future.resultNow() ).sum
    }.fold( error => fail(error.getMessage()), lines => assert(lines == expectedLineCount) )
  }