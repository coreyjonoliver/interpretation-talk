import sbt._
import Keys._

object BuildSettings {
  val VERSION = "0.1"

  lazy val basicSettings = Seq(
    version := VERSION,
    homepage := Some(new URL("https://github.com/coreyjonoliver/minipy")),
    organization := "org.coreyoliver",
    description := "A mini Python interpreter",
    startYear := Some(2014),
    licenses := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion := "2.10.3",
    resolvers ++= Dependencies.resolutionRepos,
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-deprecation",
      "-target:jvm-1.6", // use the ASM Compiler backend to generate bytecode
      "-feature", // activate checking of SIP-18 features, forces import language
      "-unchecked", // enable detailed unchecked warnings
      "-Xlog-reflective-calls", // print a message when a reflective call is generated
      "-Ywarn-adapted-args" // warn if an argument list is modified to match the receiver
    )
  )
}
