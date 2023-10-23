package objektwerks.macros

import scala.quoted.{Expr, Quotes}

object OddOrEvenMacro:
  def oddOrEvenMacro(n: Expr[Int])(using Quotes): Expr[String] = '{
    $n % 2 match
      case 0 => "even"
      case _ => "odd"
  }

  inline def oddOrEven(inline n: Int): String = ${ OddOrEvenMacro.oddOrEvenMacro( 'n ) }