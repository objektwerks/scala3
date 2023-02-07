package objektwerks

/**
 * Select Modify Run Configuration... and provide CLI args, such as: James Bond
 * then select the run triangle ( in the left margin ) and run 'greeting'
 */
object App:
  @main def greeting(first: String, last: String): Unit =
    println(s"@main greetings(first: String, last: String) => Greetings, $first $last!")