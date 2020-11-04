name := "scala3"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.0.0-M1"
libraryDependencies ++= {
  Seq(
    ("org.scalameta" %% "munit" % "0.7.16" % Test).withDottyCompat(scalaVersion.value)
  )
}
testFrameworks += new TestFramework("munit.Framework")