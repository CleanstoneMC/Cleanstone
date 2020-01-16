package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.Recipes;
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

