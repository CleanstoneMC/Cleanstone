package rocks.cleanstone.mappinggenerator

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.invoke
import rocks.cleanstone.mappinggenerator.tasks.DownloadVanillaServerTask
import rocks.cleanstone.mappinggenerator.tasks.GenerateVanillaItemMappings
import rocks.cleanstone.mappinggenerator.tasks.GenerateVanillaReportTask


class CleanstonePlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {

        tasks {
            val downloadVanillaServer = create("downloadVanillaServer", DownloadVanillaServerTask::class) {
                group = "generate"
                description = "Download the Vanilla Server for the given Version"
            }

            val generateReport = create("generateReport", GenerateVanillaReportTask::class) {
                dependsOn(downloadVanillaServer)
                group = "generate"
                description = "Generates the Report for the given Version."
            }

            val generateVanillaItemMappings = create("generateVanillaItemMappings", GenerateVanillaItemMappings::class) {
                //                dependsOn(generateReport)
                group = "generate"
                description = "Generates the Item Mappings for the given Version."
            }
        }
    }
}