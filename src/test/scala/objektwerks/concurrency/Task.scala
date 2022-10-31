package objektwerks.concurrency

import java.util.concurrent.Callable;

class FactorialTask(n: Int) extends Callable[Long]:
  def factorial(n: Int, acc: Int): Long =
      if (n < 1) then acc
      else factorial(n - 1, acc * n)

  def call(): Long = factorial(n, 1)
