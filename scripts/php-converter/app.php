<?php

require_once 'Block.php';
require_once 'Property.php';

$blocksJsonContent = file_get_contents('blocks.json');
$blocksData = json_decode($blocksJsonContent, true);

$blocks = [];
foreach ($blocksData as $blockName => $blockData) {
    $blocks[] = new Block($blockName, $blockData);
}

$vanillaBlockTypeContent = 'package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.VanillaMiningLevel;

import static rocks.cleanstone.game.material.block.vanilla.VanillaBlockProperties.*;

public enum VanillaBlockType implements BlockType {
';

$vanillaBlockTypeContent .= implode(",\n", $blocks);

$vanillaBlockTypeContent = rtrim($vanillaBlockTypeContent, ',') . ";\n\n";

$vanillaBlockTypeContent .= '
    private final String minecraftID;
    private final Property[] properties;
    private final VanillaMiningLevel miningLevel;

    VanillaBlockType(String minecraftID, Property[] properties, VanillaMiningLevel miningLevel) {
        this.minecraftID = minecraftID;
        this.properties = properties;
        this.miningLevel = miningLevel;
    }

    VanillaBlockType(String minecraftID, Property[] properties) {
        this(minecraftID, properties, VanillaMiningLevel.HAND);
    }

    VanillaBlockType(String minecraftID) {
        this(minecraftID, new Property[0], VanillaMiningLevel.HAND);
    }

    @Override
    public boolean hasBlockEntity() {
        return false;
    }

    @Override
    public int getID() {
        return ordinal();
    }

    @Override
    public String getMinecraftID() {
        return "minecraft:" + minecraftID;
    }

    @Override
    public Property[] getProperties() {
        return properties;
    }

    @Override
    public VanillaMiningLevel getMiningLevel() {
        return miningLevel;
    }
}';


$vanillaPropertiesContent = 'package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.block.state.property.type.PropertyBoolean;
import rocks.cleanstone.game.block.state.property.type.PropertyEnum;
import rocks.cleanstone.game.block.state.property.type.PropertyInteger;
import rocks.cleanstone.game.block.state.property.vanilla.*;

public class VanillaBlockProperties {
';

/**
 * @var string $propertyName
 * @var Property $property
 */
foreach (Property::$properties as $propertyName => $property) {
    $vanillaPropertiesContent .= '    public static final ' . $property->getLongPropertyType() . ' ' . $property->getPropertyName() . ' = ' . $property->getBlockPropertiesString() . ";\n";
}

$vanillaPropertiesContent .= '}
';

file_put_contents('/home/fionera/src/Cleanstone/src/main/java/rocks/cleanstone/game/material/block/vanilla/VanillaBlockType.java', $vanillaBlockTypeContent);
file_put_contents('/home/fionera/src/Cleanstone/src/main/java/rocks/cleanstone/game/material/block/vanilla/VanillaBlockProperties.java', $vanillaPropertiesContent);
