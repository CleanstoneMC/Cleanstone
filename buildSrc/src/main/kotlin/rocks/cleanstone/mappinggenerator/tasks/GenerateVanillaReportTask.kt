package rocks.cleanstone.mappinggenerator.tasks

import org.gradle.api.tasks.TaskAction

open class GenerateVanillaReportTask : RevisionAwareTask() {

    @TaskAction
    fun generateVanillaReport() {
        Runtime.getRuntime().exec(
                arrayOf("java", "-cp", getServerFile().toPath().toString(), "net.minecraft.data.Main", "--server", "--reports"),
                null,
                getServerFolder()
        )
    }
}