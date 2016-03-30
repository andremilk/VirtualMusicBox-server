name := """play-java-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  cache,
  "org.apache.directory.api" % "api-all" % "1.0.0-M14",

  //"postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final",
  "com.h2database" % "h2" % "1.4.187"
)


libraryDependencies += "com.typesafe.play" % "play-java-jpa_2.10" % "2.4.3"
playEbeanModels in Compile := Seq("models.*")


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
