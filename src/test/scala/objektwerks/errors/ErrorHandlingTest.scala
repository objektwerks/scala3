package objektwerks.errors

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.io.{Codec, Source}
import scala.util.control.Exception.*
import scala.util.control.NonFatal
import scala.util.{Success, Try, Using}

def divide(x: Int, y: Int): Either[Throwable, Int] = Try(x / y).toEither

def divide(x: String, y: String): Option[Int] =
  for {
    x <- x.toIntOption
    y <- y.toIntOption
  } yield x / y

def fileToLines(file: String): Try[Seq[String]] = 
  Using( Source.fromFile(file, Codec.UTF8.name) ) { source => source.getLines().toSeq }

def parseInt(s: String): Option[Int] = Try(s.toInt).toOption

class ErrorHandlingTest extends AnyFunSuite with Matchers:
  test("either") {
    divide(9, 3).isRight shouldBe true
    divide(9, 0).isLeft shouldBe true
    divide(9, 3).contains(3) shouldBe true
    divide(9, 3).exists(_ == 3) shouldBe true
    divide(9, 3).getOrElse(-1) shouldBe 3
    divide(9, 3).map(_ * 3).getOrElse(-1) shouldBe 9
    divide(9, 3).map(_ * 3).filterOrElse(_ == 9, -1).getOrElse(-1) shouldBe 9
    divide(3, 0) match {
      case Right(_) => fail("Should throw divide by zero error.")
      case Left(error) => error.isInstanceOf[Throwable] shouldBe true
    }
  }
  
  test("try catch handler") {
    val handler: PartialFunction[Throwable, Unit] = {
      case NonFatal(error) => error.getMessage.nonEmpty shouldBe true; ()
    }
    try "abc".toInt catch handler
  }

  test("try") {
    divide("9", "3").nonEmpty shouldBe true
    divide("9", "3").contains(3) shouldBe true
    divide("9", "3").get shouldBe 3
    divide("a", "b").isEmpty shouldBe true
    divide("a", "b").isEmpty shouldBe true
    divide("a", "b").getOrElse(-1) shouldBe -1
  }

  test("try option") {
    parseInt("a").isEmpty shouldBe true
    parseInt("1").isDefined shouldBe true
  }

  test("try using") {
    fileToLines("build.sbt").isSuccess shouldBe true
    fileToLines("sbt.sbt").isFailure shouldBe true
  }

  test("try recover") {
    val i = for {
      i <- Try("one".toInt).recover { case _ => 0 }
    } yield i
    i shouldBe Success(0)
  }

  test("all catch") {
    allCatch.opt("1".toInt).nonEmpty shouldBe true
    allCatch.opt("one".toInt).isEmpty shouldBe true
  }