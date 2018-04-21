package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class CraftRecipeRequestPacket {

    private final byte windowID;
    private final int recipe;
    private final boolean makeAll;

    public CraftRecipeRequestPacket(byte windowID, int recipe, boolean makeAll) {
        this.windowID = windowID;
        this.recipe = recipe;
        this.makeAll = makeAll;
    }

    public byte getWindowID() {
        return windowID;
    }

    public int getRecipe() {
        return recipe;
    }

    public boolean isMakeAll() {
        return makeAll;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CRAFT_RECIPE_REQUEST;
    }
}
