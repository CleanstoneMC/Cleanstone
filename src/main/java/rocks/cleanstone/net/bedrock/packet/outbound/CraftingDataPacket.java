package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.Recipes;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class CraftingDataPacket implements Packet {

    private final Recipes recipes;

    public CraftingDataPacket(Recipes recipes) {
        this.recipes = recipes;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.CRAFTING_DATA;
    }
}

