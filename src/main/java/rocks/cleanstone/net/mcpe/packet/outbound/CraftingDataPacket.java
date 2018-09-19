package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.Recipes;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CraftingDataPacket implements Packet {

    private final Recipes recipes;

    public CraftingDataPacket(Recipes recipes) {
        this.recipes =  recipes;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.CRAFTING_DATA;
    }
}

