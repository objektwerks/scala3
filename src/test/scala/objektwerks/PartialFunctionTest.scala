package objektwerks

import munit._

import scala.util.Try

val multipleByOne: PartialFunction[Int, Int] = {
  case i: Int if i != 0 => i * 1
}

val divideByOne = new PartialFunction[Int, Int] {
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
    assert( ( Try { List(0, 1, 2) map multipleByOne }.isFailure ) == true )
    assert( ( List(0, 1, 2) collect multipleByOne ) == List(1, 2) )
    assert( ( List(42, "cat") collect { case i: Int => multipleByOne(i) } ) == List(42) )

    assert( divideByOne(2) == 2 )
    assert( divideByOne(0) == 0 )
    assert( divideByOne.isDefinedAt(3) == true )
    assert( divideByOne.isDefinedAt(0) == false )

    assert( ( 1 to 3 collect isEven ) == Vector("2 even") )
    assert( ( 1 to 3 collect isOdd ) == Vector("1 odd", "3 odd") )
    assert( ( 1 to 3 collect (isEven orElse isOdd) ) == Vector("1 odd", "2 even", "3 odd") )
    assert( ( 1 to 3 map (isOdd orElse isEven) ) == Vector("1 odd", "2 even", "3 odd") )
  }
}