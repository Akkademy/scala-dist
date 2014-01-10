import com.typesafe.sbt.SbtGit._
import S3._

// so we don't require a native git install
useJGit

// The version of this build determines the Scala version to package.
// We look at the closest git tag that matches v[0-9].* to derive it.
// For testing, the version may be overridden with -Dproject.version=...
versionWithGit

Versioning.settings

s3Settings

host in upload := "downloads.typesafe.com.s3.amazonaws.com"

credentials += Credentials(Path.userHome / ".s3credentials")

ScalaDist.settings

Docs.settings

ScalaDist.platformSettings

// TODO: once we start smoke testing, we'll want to resolve against staging to
// detect any issues before promoting to maven central -- we should look up the repo url using the sonatype rest api
resolvers += "staging" at "https://oss.sonatype.org/content/repositories/orgscala-lang-1058/"

// resolvers += "local" at "file:///e:/.m2/repository"
// resolvers += Resolver.mavenLocal
// to test, run e.g., stage, or windows:packageBin, show s3-upload::mappings