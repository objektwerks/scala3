package objektwerks.classes

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

final class CacheImpl:
  private val map = mutable.Map.empty[Int, Int]
  given secret: String = "secret"

  def put(key: Int, value: Int): Option[Int] = map.put(key, value)
  def get(key: Int): Option[Int] = map.get(key)
  def size: Int = map.size
  def clear(): Unit = map.clear()

final class Cache:
  private val cache = CacheImpl()
  export cache.* // export all CacheImpl methods
  export cache.given // export all given fields

/**
 * See: https://www.baeldung.com/scala/scala-3-export
 */
final class ExportTest extends AnyFunSuite with Matchers:
  test("export"):
    val cache = Cache()
    cache.put(1, 1) shouldBe None
    cache.get(1) shouldBe Some(1)
    cache.size shouldBe 1
    cache.clear()
    cache.size shouldBe 0
    cache.secret shouldBe "secret"