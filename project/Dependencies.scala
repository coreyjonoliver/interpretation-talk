import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    "Spray" at "http://repo.spray.io/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
  )

  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  def runtime(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")

  def container(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  val specs2 = "org.specs2" %%  "specs2" % "2.2.3"
  val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.10.1"
}

