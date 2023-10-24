package objektwerks.macros

import scala.quoted.{Expr, Quotes}

// Quoting - build an AST fragment
def oddOrEvenImpl(n: Expr[Int])(using Quotes): Expr[String] = '{
  // placing $n % 2 on line 10 results in an unreachable case warning
  // adding line 9 eliminates an unreachable case warning
  val i = $n % 2
  i match
    case 0 => "even"
    case _ => "odd"
}

// Splicing - insert an AST fragment, the macro, into the program AST
inline def oddOrEven(inline n: Int): String = ${ oddOrEvenImpl( 'n ) }