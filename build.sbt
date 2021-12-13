name := "scala3"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.1.1-RC1"
libraryDependencies ++= {
  Seq(
    "com.lihaoyi" %% "upickle" % "1.4.3",
    "io.github.cquiroz" %% "scala-java-time" % "2.3.0",
    "org.scalatest" %% "scalatest" % "3.2.10" % Test
  )
}