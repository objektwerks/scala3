name := "scala3"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.0.0-M2"
libraryDependencies ++= {
  Seq(
    "org.scalameta" %% "munit" % "0.7.19" % Test
  )
}
testFrameworks += new TestFramework("munit.Framework")
parallelExecution in Test := false
semanticdbEnabled := true