package objektwerks.concurrency

import java.util.concurrent.Callable

final class FactorialTask(n: Int) extends Callable[Long]:
  def factorial(n: Int, acc: Int): Long =
      if (n < 1) then acc
      else factorial(n - 1, acc * n)

  def call(): Long = factorial(n, 1)

final class FibonacciTask(n: Int) extends Callable[Long]:
  def fibonacci(n: Int, a: Int, b: Int): Long =
    if (n == 0) then a
    else fibonacci(n -1, b, a + b)

  def call(): Long = fibonacci(n, 0, 1)