package objektwerks

object App {
  inline val title = "Scala3 Main Method App"

  @main def greeting(name: String): Unit = {
    println(s"*** $title : Greetings, $name! ***")
  }
}