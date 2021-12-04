sealed trait TestA
final case class A(a: Int) extends TestA

val a = A(1)
a.isInstanceOf[TestA]
a.isInstanceOf[Product]
a.isInstanceOf[Serializable]
a.isInstanceOf[A]

sealed trait TestB
case object B extends TestB

B.isInstanceOf[TestB]
B.isInstanceOf[Product]
B.isInstanceOf[Serializable]

sealed trait TestC
final class C(c: Int) extends TestC

val c = C(1)
c.isInstanceOf[TestC]
// c.isInstanceOf[Product]  Always false
// c.isInstanceOf[Serializable]  Always false
c.isInstanceOf[C]