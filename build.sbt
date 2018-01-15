name := "Hello"
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "4.0.2" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")