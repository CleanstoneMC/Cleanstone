plugins {
    base
    id("org.ysb33r.rust.base") version "0.2"
    id("org.ysb33r.rust.lib") version "0.2"
}

rust {
    executable(mapOf("version" to "1.34.2"))
}

tasks {
    cargoManifest {
        manifest.packageInf {
            name("ruststone")
            version("0.1.0")
            authors.add("Fionera")
        }
    }
}
