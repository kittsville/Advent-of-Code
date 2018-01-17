name := "Advent of Code"
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.2" % "test")
libraryDependencies ++= Seq("org.scalaz" %% "scalaz-core" % "7.2.18")

scalacOptions in Test ++= Seq("-Yrangepos")