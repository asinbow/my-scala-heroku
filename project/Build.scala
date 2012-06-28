import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "heroku"
    val appVersion      = "0.1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
      "postgresql" % "postgresql" % "9.1-901.jdbc4"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
