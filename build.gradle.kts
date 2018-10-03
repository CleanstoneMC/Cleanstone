import org.gradle.api.JavaVersion.VERSION_1_8
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    application
    jacoco
    id("org.springframework.boot") version "2.0.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

group = "rocks.cleanstone"
version = "0.2.0"

application {
    mainClassName = "rocks.cleanstone.Cleanstone"
}

java {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_1_8
}

repositories {
    jcenter()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter")
    compile("org.springframework.boot:spring-boot-starter-cache")
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-devtools")
    compile("org.springframework.data:spring-data-jpa")
    compile("de.codecentric:spring-boot-admin-starter-client:2.0.3-SNAPSHOT")
    compile("de.codecentric:spring-boot-admin-server-ui:2.0.3-SNAPSHOT")

    compile("io.netty:netty-all:4.1.22.Final")
    compile("com.whirvis:jraknet:2.9.8")

    compile("com.google.guava:guava:24.1-jre")
    compile("com.google.code.gson:gson:2.6.2")
    compile("org.apache.commons:commons-lang3:3.7")
    compile("org.apache.commons:commons-text:1.3")
    compile("javax.vecmath:vecmath:1.5.2")
    compile("com.github.ben-manes.caffeine:caffeine:2.6.2")
    compile("commons-io:commons-io:2.5")

    compile("javax.persistence:persistence-api:1.0.2")
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
        if (System.console() != null || System.getenv()["TERM"]?.startsWith("xterm") == true)
            systemProperty("spring.output.ansi.enabled", "always")
    }

    named<JavaExec>("run") { fixupStdIo() }
    named<BootRun>("bootRun") { fixupStdIo() }

    val adminConsoleUi by registering(Copy::class) {
        from(project("admin-ui-extension").tasks.getByName("yarn_build"))
        into("build/resources/main/META-INF/spring-boot-admin-server-ui/extensions/cleanstone")
    }

    "processResources" { dependsOn(adminConsoleUi) }
}
