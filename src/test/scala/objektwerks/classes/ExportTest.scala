package objektwerks.classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

final class CacheImpl:
  private val map = mutable.Map.empty[Int, Int]

  def put(key: Int, value: Int): Option[Int] = map.put(key, value)
  def get(key: Int): Option[Int] = map.get(key)
  def clear(): Unit = map.clear()

final class Cache:
  private val cache = CacheImpl()
  export cache.*

final class ExportTest extends AnyFunSuite with Matchers:
  test("export"):
    println("test export")