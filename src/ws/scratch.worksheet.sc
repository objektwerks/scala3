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

final case class D()
val d = D()
d.isInstanceOf[Product]
d.isInstanceOf[Serializable]

case object E
E.isInstanceOf[Product]
E.isInstanceOf[Serializable]

import scala.quoted.Type

sealed trait Z
final case class X() extends Z
final case class Y() extends Z

def matchOn(z: Z): String =
  z match
    case x: X => x.toString
    case y: Y => y.toString

matchOn( X() )
matchOn( Y() )

enum U(val abbv: String):
  case ounce extends U("oz")
  case gallon extends U("gl")
  case pounds extends U("lb")

U.ounce.abbv