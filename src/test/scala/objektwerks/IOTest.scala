package objektwerks

import munit._

import scala.collection.MapView
import scala.io.{Codec, Source}
import scala.util.{Try, Using}

class IOTest extends FunSuite {
  val utf8 = Codec.UTF8.name
  val quote = "You can avoid reality, but you cannot avoid the consequences of avoiding reality."

  test("from url") {
    val jokes = Using( Source.fromURL("http://api.icndb.com/jokes/random/", utf8) ) { source => source.mkString.split("\\W+") }
    assert( jokes.get.nonEmpty == true )
  }

  test("from file") {
    val words = Using( Source.fromFile("./LICENSE", utf8) ) { source => source.mkString.split("\\W+") }
    ( words.get.length == 1427 )
  }

  test("from input stream") {
    val words = Source.fromInputStream(getClass.getResourceAsStream("/license.mit"), utf8).mkString.split("\\W+")
    assert( words.length == 169 )
    assert( toWordCountMap(words).size == 96 )
  }

  test("from string") {
    val words = Source.fromString(quote).mkString.split("\\W+")
    assert( words.length == 13 )
  }

  test("from chars") {
    val words = Source.fromChars(quote.toCharArray).mkString.split("\\W+")
    assert( words.length == 13 )
  }

  test("from bytes") {
    val words = Source.fromBytes(quote.getBytes(utf8), utf8).mkString.split("\\W+")
    assert( words.length == 13 )
  }

  test("grouped") {
    val list = Source.fromInputStream(getClass.getResourceAsStream("/license.mit"), utf8).mkString.split("\\W+").toList
    assert( list.length == 169 )

    val words = list.grouped(list.length / 8).toList
    assert( words.length == 9 )
  }

  test("file to lines") {
    assert( fileToLines("build.sbt").isSuccess == true )
    assert( fileToLines("sbt.sbt").isFailure == true )
  }

  def toWordCountMap(words: Array[String]): MapView[String, Int] = {
    words.groupBy((word: String) => word.toLowerCase).view.mapValues(_.length)
  }

  def fileToLines(file: String): Try[Seq[String]] = Using( Source.fromFile(file, utf8) ) { source => source.getLines().toSeq }
}