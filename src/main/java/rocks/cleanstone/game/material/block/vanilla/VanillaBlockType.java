package rocks.cleanstone.game.material.block.vanilla;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.type.PropertyBoolean;
import rocks.cleanstone.game.block.state.property.type.PropertyInteger;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.VanillaMiningLevel;

public enum VanillaBlockType implements BlockType {
    AIR("air"),
    GRANITE("granite"),
    STONE("stone"),
    DIRT("dirt"),
    GRASS("grass_block", new Property[]{new PropertyBoolean("snowy", false)}),
    BEDROCK("bedrock"),
    FIRE("fire", new Property[]{new PropertyInteger("age", 0, 15, 0), new PropertyBoolean("north", false), new PropertyBoolean("east", false), new PropertyBoolean("south", false), new PropertyBoolean("west", false), new PropertyBoolean("up", false)});
    // ...

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
}
