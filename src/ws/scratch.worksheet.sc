import scala.reflect.TypeTest
sealed trait Test
case class A(a: Int) extends Test

val a = A(1)
a.isInstanceOf[Test]
a.isInstanceOf[Product]
a.isInstanceOf[Serializable]
a.isInstanceOf[A]
