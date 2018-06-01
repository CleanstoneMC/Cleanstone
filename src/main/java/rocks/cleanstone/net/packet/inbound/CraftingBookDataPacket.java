package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CraftingBookDataPacket implements Packet {

    private final int type;

    // Type 0
    private final int recipeID;

    //Type 1
    private final boolean craftingBookOpen;
    private final boolean craftingFilter;

    /**
     * Type 0 Constructor
     */
    public CraftingBookDataPacket(int type, int recipeID) {
        this.type = type;
        this.recipeID = recipeID;
        this.craftingBookOpen = false;
        this.craftingFilter = false;
    }

    /**
     * Type 1 Constructor
     */
    public CraftingBookDataPacket(int type, boolean craftingBookOpen, boolean craftingFilter) {
        this.type = type;
        this.recipeID = -1;
        this.craftingBookOpen = craftingBookOpen;
        this.craftingFilter = craftingFilter;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CRAFTING_BOOK_DATA;
    }
}
