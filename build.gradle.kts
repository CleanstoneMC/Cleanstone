import org.gradle.api.JavaVersion.VERSION_11
import org.springframework.boot.gradle.tasks.run.BootRun

buildscript {
    repositories {
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.1.0.M4")
    }
}

plugins {
    java
    application
    jacoco
}

apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "rocks.cleanstone"
version = "0.2.0"

application {
    mainClassName = "rocks.cleanstone.Cleanstone"
}

java {
    sourceCompatibility = VERSION_11
    targetCompatibility = VERSION_11
}

repositories {
    jcenter()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter")
    compile("org.springframework.boot:spring-boot-starter-cache")
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-devtools")
    compile("de.codecentric:spring-boot-admin-starter-client:2.1.0-SNAPSHOT")
    compile("de.codecentric:spring-boot-admin-server-ui:2.1.0-SNAPSHOT")

    compile("io.netty:netty-all:4.1.22.Final")
    compile("com.whirvis:jraknet:2.9.8")

    compile("com.google.guava:guava:24.1-jre")
    compile("com.google.code.gson:gson:2.6.2")
    compile("org.apache.commons:commons-lang3:3.7")
    compile("org.apache.commons:commons-text:1.3")
    compile("javax.vecmath:vecmath:1.5.2")
    compile("com.github.ben-manes.caffeine:caffeine:2.6.2")
    compile("commons-io:commons-io:2.5")

    compile("org.fusesource.leveldbjni:leveldbjni-all:1.8")

    testCompile("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

jacoco {
    toolVersion = "0.8.2"
}

tasks {
    named<Test>("test") { useJUnitPlatform() }

    fun JavaExec.fixupStdIo() {
        standardInput = System.`in`
        if (System.console() != null || System.getenv()["TERM"]?.startsWith("xterm") == true) {
            systemProperty("spring.output.ansi.enabled", "always")
        }
    }

    named<JavaExec>("run") { fixupStdIo() }
    named<BootRun>("bootRun") { fixupStdIo() }

    named<ProcessResources>("processResources") {
        from(project("admin-ui-extension").tasks.named("yarn_build")) {
            into("META-INF/spring-boot-admin-server-ui/extensions/cleanstone")
        }
    }
}
