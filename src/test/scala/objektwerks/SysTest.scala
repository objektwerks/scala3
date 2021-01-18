package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.sys.SystemProperties
import scala.sys.process.Process

class SysTest extends AnyFunSuite with Matchers:
  test("system properties") {
    val properties = SystemProperties()
    properties.contains("java.runtime.name") shouldBe true

    properties += ("objekt" -> "werks")
    properties.contains("objekt") shouldBe true

    properties -= "objekt"
    properties.getOrElse("objekt", "empty") shouldBe "empty"
  }

  test("process") {
    val file = Process("ls").lazyLines.find(file => file == "build.sbt")
    file.getOrElse("empty") shouldBe "build.sbt"

    val line = Process("find build.sbt").lazyLines.headOption
    line.getOrElse("empty") shouldBe "build.sbt"

    val lines = Process("cat .gitignore").lazyLines
    lines.length shouldBe 6
  }