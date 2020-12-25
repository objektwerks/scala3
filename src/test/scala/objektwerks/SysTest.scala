package objektwerks

import munit._

import scala.sys.SystemProperties
import scala.sys.process.Process

class SysTest extends FunSuite {
  test("system properties") {
    val properties = SystemProperties()
    assert( properties.contains("java.runtime.name") == true )

    properties += ("objekt" -> "werks")
    assert( properties.contains("objekt") == true )

    properties -= "objekt"
    assert( properties.getOrElse("objekt", "empty") == "empty" )
  }

  test("process") {
    val file = Process("ls").lazyLines.find(file => file == "build.sbt")
    assert( file.getOrElse("empty") == "build.sbt" )

    val line = Process("find build.sbt").lazyLines.headOption
    assert( line.getOrElse("empty") == "build.sbt" )

    val lines = Process("cat .gitignore").lazyLines
    assert( lines.length == 6 )
  }
}