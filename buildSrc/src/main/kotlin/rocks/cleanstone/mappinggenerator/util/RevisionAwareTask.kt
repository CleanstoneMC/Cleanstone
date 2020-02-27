package rocks.cleanstone.mappinggenerator.util

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.options.Option
import java.io.File

open class RevisionAwareTask : DefaultTask() {
    companion object {
        lateinit var rev: String
    }

    @Input
    protected open fun getRev(): String {
        return rev
    }

    @Option(option = "rev", description = "Configures the revision for which to generate the mappings.")
    protected open fun setRev(newRev: String) {
        rev = newRev
    }


    @Internal
    protected fun getServerFolder(): File {
        return project.buildDir
                .resolve("server-cache").also { it.mkdir() }
                .resolve("minecraft-$rev").also { it.mkdir() }
    }

    @Internal
    protected fun getServerFile(): File {
        return getServerFolder()
                .resolve("server-$rev.jar")
    }


    @Internal
    protected fun getVersionString(): String {
        return getRev().replace('.', '_')
    }

    @Internal
    protected val srcRoot = project.rootDir
            .resolve("src")
            .resolve("main")
            .resolve("java")
}