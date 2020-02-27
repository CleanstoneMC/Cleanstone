plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("cleanstone-plugin") {
            id = "cleanstone"
            implementationClass = "rocks.cleanstone.mappinggenerator.CleanstonePlugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.beust:klaxon:5.2")
    implementation("com.squareup:javapoet:1.12.1")
}