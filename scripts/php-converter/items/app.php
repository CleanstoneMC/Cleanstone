<?php

require_once 'Item.php';

$itemsJsonContent = file_get_contents('items.json');
$itemsData = json_decode($itemsJsonContent, true);

$items = [];
foreach ($itemsData as $itemName => $itemData) {
    $items[] = new Item($itemName,  $itemData);
}

$vanillaItemTypeContent = 'package rocks.cleanstone.game.material.item.vanilla;

import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.item.ItemType;

public enum VanillaItemType implements ItemType {
';

$vanillaItemTypeContent .= implode(",\n", $items);

$vanillaItemTypeContent = rtrim($vanillaItemTypeContent, ',') . ";\n\n";

$vanillaItemTypeContent .= '    private final String minecraftID;
    private final int stackSize;

    VanillaItemType(String minecraftID, int stackSize) {
        this.minecraftID = minecraftID;
        this.stackSize = stackSize;
    }

    VanillaItemType(String minecraftID) {
        this(minecraftID, 64);
    }

    @Override
    public int getStackSize() {
        return stackSize;
    }

    @Override
    public void rightClickAir(Entity entity, ItemStack holding) {
    }

    @Override
    public void rightClickBlock(Entity entity, Block block, Face face) {
    }

    @Override
    public int getID() {
        return -ordinal();
    }

    @Override
    public String getMinecraftID() {
        return "minecraft:" + minecraftID;
    }
}';

file_put_contents('VanillaItemType.java', $vanillaItemTypeContent);