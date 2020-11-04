name := "scala3"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := dottyLatestNightlyBuild.get
libraryDependencies ++= {
  val scalaTestVersion = "3.2.2"
  Seq(
    ("org.scalactic" %% "scalactic" % scalaTestVersion % Test).withDottyCompat(scalaVersion.value),
    ("org.scalatest" %% "scalatest" % scalaTestVersion % Test).withDottyCompat(scalaVersion.value)
  )
}