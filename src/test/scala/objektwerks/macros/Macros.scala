package objektwerks.macros

import scala.quoted.{Expr, Quotes}

// Quote - builds an AST fragment
def oddOrEvenImpl(n: Expr[Int])(using Quotes): Expr[String] = '{
  $n % 2 match
    case 0 => "even"
    case _ => "odd"
}

// Splice - inserts an AST fragment, the macro, into the program AST
inline def oddOrEven(inline n: Int): String = ${ oddOrEvenImpl( 'n ) }