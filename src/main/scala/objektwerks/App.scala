package objektwerks

object App {
  val title = "App"

  def main(args: Array[String]): Unit = {
    println(s"*** App main args [${args.length}]: ${args.foreach(println)}")
  }
}