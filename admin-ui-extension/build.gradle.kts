plugins {
  base
  id("com.moowork.node") version "1.2.0"
}

node {
  version = "11.1.0"
  download = true
}

tasks {
  val yarn by existing

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
  named<Task>("yarn_watch") { configureYarnTask() }
  val yarn_clean by existing

  "assemble" { dependsOn(yarn_build) }
  "check" { dependsOn(yarn_lint) }
  "clean" { dependsOn(yarn_clean) }
}
