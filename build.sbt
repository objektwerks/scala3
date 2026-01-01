name := "scala3"
organization := "objektwerks"
version := "1.0.0"
scalaVersion := "3.8.0-RC4"
libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
