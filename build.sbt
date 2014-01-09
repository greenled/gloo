import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.Keys._

name := "Pegotes"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
	filters
)

play.Project.playJavaSettings

//***********************
// Empaquetado para Linux
//***********************

name in Linux := "pegotes"

maintainer := "Juan Carlos Mejías Rodríguez <juan.mejias@reduc.edu.cu>"

packageSummary := "Un pastebin realmente sencillo escrito en Java"

packageDescription := "Hecho con el framework web Play!. Ofrece tres APIs: Xml, Json, y texto plano."