import org.gradle.api.JavaVersion.VERSION_1_8
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    application
    jacoco
    id("io.franzbecker.gradle-lombok") version "1.14"
    id("org.springframework.boot") version "2.1.0.RELEASE"
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

lombok {
	version = "1.18.2"
}

repositories {
    jcenter()
}

dependencies {
    compile("org.springframework.boot:spring-boot-starter")
    compile("org.springframework.boot:spring-boot-starter-cache")
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-devtools")
    compile("de.codecentric:spring-boot-admin-starter-client:2.1.0")
    compile("de.codecentric:spring-boot-admin-starter-server:2.1.0")

    compile("io.netty:netty-all:4.1.31.Final")
    compile("com.whirvis:jraknet:2.9.8")

    compile("com.google.guava:guava:27.0-jre")
    compile("com.google.code.gson:gson:2.8.5")
    compile("org.apache.commons:commons-lang3:3.8.1")
    compile("org.apache.commons:commons-text:1.6")
    compile("javax.vecmath:vecmath:1.5.2")
    compile("com.github.ben-manes.caffeine:caffeine:2.6.2")
    compile("commons-io:commons-io:2.6")

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

    withType(JavaCompile::class) {
        options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked"))
    }

    named<ProcessResources>("processResources") {
        from(project("admin-ui-extension").tasks.named("yarn_build")) {
            into("META-INF/spring-boot-admin-server-ui/extensions/cleanstone")
        }
    }
}
