name := "scala3"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := dottyLatestNightlyBuild.get
libraryDependencies ++= {
  Seq(
    ("org.scalatest" %% "scalatest" % "3.2.2" % Test).withDottyCompat(scalaVersion.value)
  )
}