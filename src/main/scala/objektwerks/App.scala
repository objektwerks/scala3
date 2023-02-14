package objektwerks

/**
 * No object required for Scala 3 app!
 *
 * To run via IDE:
 * 1. select run triangle ( in left margin ) > Modify Run Configuration...
 * 2. provide CLI args, such as: Fred Flintstone
 * 3. select run triangle > run 'greeting'
 *
 * To run via terminal:
 * 1. sbt "run Fred Flintstone"
 */
@main def greeting(first: String, last: String): Unit =
  println(s"@main greetings(first: String, last: String) => Greetings, $first $last!")