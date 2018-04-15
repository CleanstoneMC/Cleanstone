package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.InboundPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

public class ChatMessagePacket extends InboundPacket {

    private final String message;

    public ChatMessagePacket(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CHAT_MESSAGE;
    }
}
