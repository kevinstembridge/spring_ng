
import com.github.gradle.node.npm.task.NpxTask


plugins {
  java
  id("com.github.node-gradle.node") version "3.5.1"
}

node {
  download.set(true)
}


tasks.register<NpxTask>("buildAngularApp") {
  dependsOn(tasks.npmInstall)
  command.set("ng")
  args.set(listOf("build", "--configuration", "production"))
  inputs.files("package.json", "package-lock.json", "angular.json", "tsconfig.json", "tsconfig.app.json")
  inputs.dir("src")
  inputs.dir(fileTree("node_modules").exclude(".cache"))
  outputs.dir("dist")
}


tasks.named("compileJava") {
    dependsOn("buildAngularApp")
}

