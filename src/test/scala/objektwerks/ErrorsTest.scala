package objektwerks

import munit._

import scala.io.{Codec, Source}
import scala.util.control.Exception._
import scala.util.control.NonFatal
import scala.util.{Success, Try, Using}

def divide(x: Int, y: Int): Either[Throwable, Int] = Try(x / y).toEither

def divide(x: String, y: String): Try[Int] = {
  for {
    x <- Try(x.toInt)
    y <- Try(y.toInt)
  } yield x / y
}

def fileToLines(file: String): Try[Seq[String]] = 
  Using( Source.fromFile(file, Codec.UTF8.name) ) { source => source.getLines().toSeq }

def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

class ErrorsTest extends FunSuite {
  test("either") {
    assert( divide(9, 3).isRight == true )
    assert( divide(9, 0).isLeft == true )
    assert( divide(9, 3).contains(3) == true )
    assert( divide(9, 3).exists(_ == 3) == true )
    assert( divide(9, 3).getOrElse(-1) == 3 )
    assert( divide(9, 3).map(_ * 3).getOrElse(-1) == 9 )
    assert( divide(9, 3).map(_ * 3).filterOrElse(_ == 9, -1).getOrElse(-1) == 9 )
    divide(3, 0) match {
      case Right(_) => throw new Exception("Should throw divide by zero error.")
      case Left(error) => error.isInstanceOf[Throwable] == true
    }
  }
  
  test("try catch handler") {
    val handler: PartialFunction[Throwable, Unit] = {
      case NonFatal(error) => error.getMessage.nonEmpty == true; ()
    }
    try "abc".toInt catch handler
  }

  test("try") {
    assert( divide("9", "3").isSuccess == true )
    assert( divide("9", "3").toOption.contains(3) == true )
    assert( divide("9", "3").get == 3 )
    assert( divide("a", "b").isFailure == true )
    assert( divide("a", "b").toOption.isEmpty == true )
    assert( divide("a", "b").getOrElse(-1) == -1 )
  }

  test("try option") {
    assert( parseInt("a").isEmpty == true )
    assert( parseInt("1").isDefined == true )
  }

  test("try using") {
    assert( fileToLines("build.sbt").isSuccess == true )
    assert( fileToLines("sbt.sbt").isFailure == true )
  }

  test("try recover") {
    val i = for {
      i <- Try("one".toInt).recover { case _ => 0 }
    } yield i
    assert( i == Success(0) )
  }

  test("all catch") {
    assert( allCatch.opt("1".toInt).nonEmpty == true )
    assert( allCatch.opt("one".toInt).isEmpty == true )
  }
}