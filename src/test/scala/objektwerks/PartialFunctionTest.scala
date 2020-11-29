package objektwerks

import munit._

import scala.util.Try

val divideByOne: PartialFunction[Int, Int] = {
  case i: Int if i != 0 => i / 1
}

val divideByOneExt = new PartialFunction[Int, Int] {
  def apply(i: Int): Int = i / 1
  def isDefinedAt(i: Int): Boolean = i != 0
}

def isEven: PartialFunction[Int, String] = {
  case i if i % 2 == 0 => s"$i even"
}

def isOdd: PartialFunction[Int, String] = {
  case i if i % 2 == 1 => s"$i odd"
}

class PartialFunctionTest extends FunSuite {
  test("partial function") {
    assert( ( Try { List(0, 1, 2) map divideByOne }.isFailure ) == true )
    assert( ( List(0, 1, 2) collect divideByOne ) == List(1, 2) )
    assert( ( List(42, "cat") collect { case i: Int => divideByOne(i) } ) == List(42) )

    assert( divideByOneExt(2) == 2 )
    assert( divideByOneExt(0) == 0 )
    assert( divideByOneExt.isDefinedAt(3) == true )
    assert( divideByOneExt.isDefinedAt(0) == false )

    assert( ( 1 to 3 collect isEven ) == Vector("2 even") )
    assert( ( 1 to 3 collect isOdd ) == Vector("1 odd", "3 odd") )
    assert( ( 1 to 3 collect (isEven orElse isOdd) ) == Vector("1 odd", "2 even", "3 odd") )
    assert( ( 1 to 3 map (isEven orElse isOdd) ) == Vector("1 odd", "2 even", "3 odd") )
  }
}