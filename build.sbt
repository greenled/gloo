import com.typesafe.sbt.SbtNativePackager._
import com.typesafe.sbt.packager.Keys._

name := "Pegotes"

version := "1.0-SNAPSHOT-2"

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

debianPackageDependencies in Debian := Seq("openjdk-7-jre | openjdk-6-jre")

debianMaintainerScripts <+= (sourceDirectory in Debian) map { (dir) =>
  (
    (dir / "postinst") -> "postinst"
  )
}

debianMaintainerScripts <+= (sourceDirectory in Debian) map { (dir) =>
  (
    (dir / "postrm") -> "postrm"
  )
}

debianMaintainerScripts <+= (sourceDirectory in Debian) map { (dir) =>
  (
    (dir / "prerm") -> "prerm"
  )
}

linuxPackageMappings in Debian <+= (sourceDirectory in Debian) map { (dir) =>
  (packageMapping(
    (dir / "README.Debian") -> "/usr/share/doc/pegotes/README.Debian.gz"
  ) withUser "root" withGroup "root" withPerms "0644" gzipped) asDocs()
}

linuxPackageMappings in Debian <+= (sourceDirectory in Debian) map { (dir) =>
  (packageMapping(
    (dir / "changelog") -> "/usr/share/doc/pegotes/changelog.Debian.gz"
  ) withUser "root" withGroup "root" withPerms "0644" gzipped) asDocs()
}

linuxPackageMappings in Debian <+= (sourceDirectory in Debian) map { (dir) =>
  (packageMapping(
    (dir / "copyright") -> "/usr/share/doc/pegotes/copyright"
  ) withUser "root" withGroup "root" withPerms "0644") asDocs()
}

linuxPackageMappings in Debian <+= (sourceDirectory in Debian) map { (dir) =>
  (packageMapping(
    (dir / "manpage") -> "/usr/share/man/man1/pegotes.1.gz"
  ) withUser "root" withGroup "root" withPerms "0644" gzipped) asDocs()
}

linuxPackageMappings in Debian <+= (sourceDirectory in Debian) map { (dir) =>
  (packageMapping(
    (dir / "pegotesd") -> "/etc/init.d/pegotesd"
  ) withUser "root" withGroup "root" withPerms "0755") asDocs()
}
