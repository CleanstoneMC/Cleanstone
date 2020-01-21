plugins {
  base
  id("com.github.node-gradle.node") version "2.2.0"
}

node {
  version = "13.6.0"
  download = true
}

tasks {
  fun Task.configureYarnTask() {
    dependsOn(yarn)
    inputs.files(
      "src/",
      "babel.config.js",
      "vue.config.js",
      "yarn.lock"
    )
    outputs.dir("dist")
  }

  val yarn_lint by existing { configureYarnTask() }
  val yarn_build by existing {
    configureYarnTask()
    dependsOn(yarn_lint)
  }
  "yarn_watch" { configureYarnTask() }
  val yarn_clean by existing { dependsOn(yarn) }

  assemble { dependsOn(yarn_build) }
  check { dependsOn(yarn_lint) }
  clean { dependsOn(yarn_clean) }
}
