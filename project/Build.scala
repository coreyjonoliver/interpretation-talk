import sbt.Keys._
import sbt._

object Build extends Build {

  import BuildSettings._
  import Dependencies._

  lazy val root = Project(id = "calc", base = file("."))
    .settings(libraryDependencies ++=
    test(specs2, scalaCheck)
    )
}
