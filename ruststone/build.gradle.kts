plugins {
    base
    id("org.ysb33r.rust.base") version "0.2"
    id("org.ysb33r.rust.lib") version "0.2"
}

rust {
    executable(mapOf(Pair("version", "1.34.2")))
    cargoToml("./Cargo.toml")
}



tasks {
//    cargoManifest {
//        manifest {
//            packageInf {
//                name("hello")
//            }
//        }
//    }
}


//cargo {
//  module = "./src"
//  targets = ["x86"]
//}