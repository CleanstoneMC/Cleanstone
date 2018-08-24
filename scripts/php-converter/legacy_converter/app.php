<?php /** @noinspection ALL */

require_once 'Item.php';

$itemsData = include 'data_1.12.2.php';
//$itemsJsonContent = file_get_contents('formatted_1.12.2.json');
//$itemsData = json_decode($itemsJsonContent, true);

$items = [];
foreach ($itemsData as $key => $itemData) {
    $items[] = new Item($itemData);
}

$protocolItemTypeMappingContent = 'package rocks.cleanstone.net.minecraft.protocol.v1_12_2;

import rocks.cleanstone.game.material.item.mapping.SimpleItemTypeMapping;
import rocks.cleanstone.game.material.item.vanilla.VanillaItemType;

import static rocks.cleanstone.game.material.item.vanilla.VanillaItemType.*;

public class ProtocolItemTypeMapping extends SimpleItemTypeMapping<Integer> {
    public ProtocolItemTypeMapping() {
        super(VanillaItemType.STONE);
';

/** @var Item $item */
foreach ($items as $item) {
    if (strpos($item->getProtocolID(), ':') === false) {
        $protocolItemTypeMappingContent .= '        setID(' . $item->getItemEnumName() . ', ' . $item->getProtocolID() . ');' . "\n";
    } else {
        $protocolItemTypeMappingContent .= '////      setID(' . $item->getItemEnumName() . ', ' . $item->getProtocolID() . '); //TODO: Add Metadata workaround' . "\n";
    }
}


$protocolItemTypeMappingContent .= '    }
}
';

file_put_contents(__DIR__ . '/../../../src/main/java/rocks/cleanstone/net/minecraft/protocol/v1_12_2/ProtocolItemTypeMapping.java', $protocolItemTypeMappingContent);