package objektwerks

object App {
  private val title = "Scala3 @main App"

  @main def greeting(name: String): Unit = {
    println(s"*** $title : Greetings, $name! ***")
  }
}