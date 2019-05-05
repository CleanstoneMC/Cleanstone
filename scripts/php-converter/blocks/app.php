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

import rocks.cleanstone.game.block.state.property.PropertyDefinition;
import rocks.cleanstone.game.block.state.property.vanilla.*;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.VanillaMiningLevel;

import static rocks.cleanstone.game.block.state.property.PropertyDefinition.arrayOf;
import static rocks.cleanstone.game.block.state.property.PropertyDefinition.definitionOf;
import static rocks.cleanstone.game.material.block.vanilla.VanillaBlockProperties.*;

public enum VanillaBlockType implements BlockType {
';

$vanillaBlockTypeContent .= implode(",\n", $blocks);

$vanillaBlockTypeContent = rtrim($vanillaBlockTypeContent, ',') . ";\n\n";

$vanillaBlockTypeContent .= '
    private final String minecraftID;
    private final PropertyDefinition[] properties;
    private final VanillaMiningLevel miningLevel;

    VanillaBlockType(String minecraftID, PropertyDefinition[] properties, VanillaMiningLevel miningLevel) {
        this.minecraftID = minecraftID;
        this.properties = properties;
        this.miningLevel = miningLevel;
    }

    VanillaBlockType(String minecraftID, PropertyDefinition[] properties) {
        this(minecraftID, properties, VanillaMiningLevel.HAND);
    }

    VanillaBlockType(String minecraftID) {
        this(minecraftID, new PropertyDefinition[0], VanillaMiningLevel.HAND);
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
    public PropertyDefinition[] getProperties() {
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

$protocolBlockStateMappingContent = 'package rocks.cleanstone.net.minecraft.protocol.v1_14;

import rocks.cleanstone.game.block.state.BlockState;
import rocks.cleanstone.game.block.state.mapping.ModernBlockStateMapping;

import static rocks.cleanstone.game.material.block.vanilla.VanillaBlockType.*;

public class ProtocolBlockStateMapping_v1_14 extends ModernBlockStateMapping {

    public ProtocolBlockStateMapping_v1_14() {
        super(BlockState.of(STONE));
';

/** @var Block $block */
foreach ($blocks as $block) {
    $protocolBlockStateMappingContent .= '        setBaseID(' . $block->getBlockEnumName() .  ', ' . $block->getBaseID() . ');' . "\n";
}

//        setBaseID(VanillaBlockType.AIR, 0);
//        setBaseID(VanillaBlockType.STONE, 1);
//        setBaseID(VanillaBlockType.GRANITE, 2);
//        // ...
$protocolBlockStateMappingContent .= '    }
}
';

//file_put_contents('VanillaBlockType.java', $vanillaBlockTypeContent);
//file_put_contents('VanillaBlockProperties.java', $vanillaPropertiesContent);
//file_put_contents('ProtocolBlockStateMapping_v1_14.java', $protocolBlockStateMappingContent);
file_put_contents('/home/leon/Programming/WorkSpace/Cleanstone/src/main/java/rocks/cleanstone/game/material/block/vanilla/VanillaBlockType.java', $vanillaBlockTypeContent);
file_put_contents('/home/leon/Programming/WorkSpace/Cleanstone/src/main/java/rocks/cleanstone/game/material/block/vanilla/VanillaBlockProperties.java', $vanillaPropertiesContent);
file_put_contents('/home/leon/Programming/WorkSpace/Cleanstone/src/main/java/rocks/cleanstone/net/minecraft/protocol/v1_14/ProtocolBlockStateMapping_v1_14.java', $protocolBlockStateMappingContent);
