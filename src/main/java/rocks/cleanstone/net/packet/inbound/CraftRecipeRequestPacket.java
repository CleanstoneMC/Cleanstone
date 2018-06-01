package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CraftRecipeRequestPacket implements Packet {

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
