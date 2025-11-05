name := "scala3"
organization := "objektwerks"
version := "1.0.0"
scalaVersion := "3.7.4-RC3"
libraryDependencies ++= {
  Seq(
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
