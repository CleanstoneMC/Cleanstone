package rocks.cleanstone.mappinggenerator.util

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.gradle.api.tasks.Internal

open class ItemReportTask : RevisionAwareTask() {

    private val parser = Parser.default()

    @Internal
    protected fun getSortedItemList(): ArrayList<Item> {
        return getItemList().also { it.sortBy { i -> i.protocolID } }
    }

    @Internal
    protected fun getItemList(): ArrayList<Item> {
        val itemList = ArrayList<Item>()

        val itemsFile = getServerFolder().resolve("generated").resolve("reports").resolve("items.json")
        if (itemsFile.exists()) {
            val itemMapping = parser.parse(itemsFile.inputStream()) as JsonObject

            itemMapping.forEach { entry ->
                val protocolID = (entry.value as JsonObject).int("protocol_id") ?: return@forEach

                itemList.add(Item(entry.key, protocolID))
            }

            return itemList
        }


        val registriesFile = getServerFolder().resolve("generated").resolve("reports").resolve("registries.json")
        if (registriesFile.exists()) {
            val itemMapping = parser.parse(registriesFile.inputStream()) as JsonObject

            itemMapping.obj("minecraft:item")?.obj("entries")?.forEach { entry ->
                val protocolID = (entry.value as JsonObject).int("protocol_id") ?: return@forEach

                itemList.add(Item(entry.key, protocolID))
            }

            return itemList
        }

        throw RuntimeException("can't find item report")
    }
}