package objektwerks

object App {
  val title = "Scala3"

  def main(args: Array[String]): Unit = {
    println(s"*** App [$title] main args [${args.length}]: ${args.foreach(println)}")
  }
}