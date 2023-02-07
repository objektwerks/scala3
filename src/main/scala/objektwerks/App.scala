package objektwerks

/**
 * To run via IDE:
 * 1. select run triangle ( in left margin ) > Modify Run Configuration...
 * 2. provide CLI args, such as: James Bond
 * 3. select the run triangle > run 'greeting'
 */
object App:
  @main def greeting(first: String, last: String): Unit =
    println(s"@main greetings(first: String, last: String) => Greetings, $first $last!")