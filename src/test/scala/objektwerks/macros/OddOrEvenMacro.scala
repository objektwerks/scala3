package objektwerks.macros

import scala.quoted.{Expr, Quotes}

object OddOrEvenMacro:
  // Quote - builds an AST fragment
  def oddOrEvenMacro(n: Expr[Int])(using Quotes): Expr[String] = '{
    $n % 2 match
      case 0 => "even"
      case _ => "odd"
  }

  // Splice - inserts an AST fragment into the program AST
  inline def oddOrEven(inline n: Int): String = ${ OddOrEvenMacro.oddOrEvenMacro( 'n ) }