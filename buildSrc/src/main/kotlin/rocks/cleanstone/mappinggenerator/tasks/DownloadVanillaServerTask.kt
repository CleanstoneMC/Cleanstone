package rocks.cleanstone.mappinggenerator.tasks

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.nio.file.Files

const val VERSION_MANIFEST = "https://launchermeta.mojang.com/mc/game/version_manifest.json"

open class DownloadVanillaServerTask : RevisionAwareTask() {

    @TaskAction
    fun downloadVanillaServer() {
        val parser: Parser = Parser.default()
        val manifestUrl = URI.create(VERSION_MANIFEST).toURL()
        val manifest = parser.parse(manifestUrl.openStream()) as JsonObject

        val version = manifest.array<JsonObject>("versions")
                ?.find { o -> o.string("id") == rev }
                ?: throw RuntimeException("Unknown Revision")

        val versionManifestString = version.string("url")
                ?: throw RuntimeException("Can't find Version Url")
        val versionManifestUrl = URI.create(versionManifestString).toURL()

        val versionManifest = parser.parse(versionManifestUrl.openStream()) as JsonObject
        val versionDownloadString = versionManifest.obj("downloads")
                ?.obj("server")
                ?.string("url")
                ?: throw RuntimeException("Can't find Download Url")

        val versionDownloadUrl = URI.create(versionDownloadString).toURL()

        val cacheDir = getServerFile().toPath()

        Files.deleteIfExists(cacheDir)
        Files.copy(versionDownloadUrl.openStream(), cacheDir)
    }
}