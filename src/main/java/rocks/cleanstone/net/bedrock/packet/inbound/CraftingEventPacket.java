package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

public class CraftingEventPacket implements Packet {

    private final byte windowID;
    private final int recipeType;
    private final UUID recipeID;
    private final ItemStack input;
    private final ItemStack result;

    public CraftingEventPacket(byte windowID, int recipeType, UUID recipeID, ItemStack input, ItemStack result) {
        this.windowID = windowID;
        this.recipeType = recipeType;
        this.recipeID = recipeID;
        this.input = input;
        this.result = result;
    }

    public byte getWindowID() {
        return windowID;
    }

    public int getRecipeType() {
        return recipeType;
    }

    public UUID getRecipeID() {
        return recipeID;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.CRAFTING_EVENT;
    }
}

