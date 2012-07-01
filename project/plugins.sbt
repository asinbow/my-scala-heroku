// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Maven Central Server" at "http://repo1.maven.org/maven2/"

resolvers += "JBoss repository" at "https://repository.jboss.org/nexus/content/repositories/"

resolvers += "java.net Maven2 Repository" at "http://download.java.net/maven/2"

resolvers += "Scala Tools Releases" at "http://scala-tools.org/repo-releases/"

resolvers += "Scala Tools Releases" at "http://scala-tools.org/repo-releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.0")
