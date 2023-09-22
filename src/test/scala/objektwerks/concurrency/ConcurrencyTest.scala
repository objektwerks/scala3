package objektwerks.concurrency

import java.time.Instant
import java.util.UUID
import java.util.concurrent.{Callable, Executors, StructuredTaskScope}

import org.scalatest.funsuite.AnyFunSuite

import scala.io.{Codec, Source}
import scala.jdk.CollectionConverters.*
import scala.util.{Failure, Success, Using}

final class FileLineCountTask(file: String) extends Callable[Int]:
  def call(): Int = Using( Source.fromFile(file, Codec.UTF8.name) ) { source => source.getLines().length }.get

/**
  * Virtual Threads: openjdk.org/jeps/425
  * Structured Concurrency: openjdk.org/jeps/428
  * ScopedValue: openjdk.org/jeps/429
  * Loom: www.marcobehler.com/guides/java-project-loom
  * Using: scala-lang.org/api/3.x/scala/util/Using$.html# | www.baeldung.com/scala/try-with-resources
  */
final class ConcurrencyTest extends AnyFunSuite:
  val tasks = List( FileLineCountTask("./data/data.a.csv"), FileLineCountTask("./data/data.b.csv") )
  val expectedLineCount = 540_959

  test("virtual threads submit") {
    Using( Executors.newVirtualThreadPerTaskExecutor() ) { executor =>
      val aFuture = executor.submit( () => FileLineCountTask("./data/data.a.csv").call() )
      val bFuture = executor.submit( () => FileLineCountTask("./data/data.b.csv").call() )
      aFuture.get() + bFuture.get()
    }.fold( error => fail(error.getMessage), lines => assert(lines == expectedLineCount) )
  }

  test("virtual threads invoke all") {
    Using( Executors.newVirtualThreadPerTaskExecutor() ) { executor =>
      executor.invokeAll( tasks.asJava ).asScala.map( future => future.get() ).sum
    }.fold( error => fail(error.getMessage), lines => assert(lines == expectedLineCount) )
  }

  test("structured concurrency join") {
    val lines = Using( StructuredTaskScope.ShutdownOnFailure() ) { scope =>
      val alines = scope.fork( () => FileLineCountTask("./data/data.a.csv").call() )
      val blines = scope.fork( () => FileLineCountTask("./data/data.b.csv").call() )
      scope.join()
      scope.throwIfFailed()
      alines.get() + blines.get()
    }
    lines match
      case Success(count) => assert(count == expectedLineCount)
      case Failure(error) => fail(error.getMessage)
  }

  test("structured concurrency join until") {
    Using( StructuredTaskScope.ShutdownOnFailure() ) { scope =>
      val futures = tasks.map( task => scope.fork( () => task.call() ) )
      scope.joinUntil( Instant.now().plusMillis(3000) )
      scope.throwIfFailed()
      futures.map( future => future.get() ).sum
    }.fold( error => fail(error.getMessage), lines => assert(lines == expectedLineCount) )
  }

  test("structured concurrency race") {
    Using( StructuredTaskScope.ShutdownOnSuccess[Int]() ) { scope =>
      tasks.foreach( task => scope.fork( () => task.call() ) )
      scope.joinUntil( Instant.now().plusMillis(3000) )
      scope.result()
    }.fold( error => fail(error.getMessage), lines => assert(lines == 270_562 || lines == 270_397) )
  }

  test("scoped value") {
    val license: ScopedValue[String] = ScopedValue.newInstance()
    val uuid = UUID.randomUUID.toString
    val count = ScopedValue
      .where(license, uuid)
      .call { () => if license.get.nonEmpty then 1 else -1 }
    assert(count == 1)
    assert(!license.isBound)
  }