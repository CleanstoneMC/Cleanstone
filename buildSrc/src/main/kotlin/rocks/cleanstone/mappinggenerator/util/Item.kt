package rocks.cleanstone.mappinggenerator.util

class Item(val itemName: String, val protocolID: Int) {
    fun getEnumName(): String = getShortName().toUpperCase()
    fun getShortName(): String = itemName.split(':').last()
}