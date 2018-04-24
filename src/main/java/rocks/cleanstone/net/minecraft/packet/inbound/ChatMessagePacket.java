package rocks.cleanstone.net.minecraft.packet.inbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftInboundPacketType;

public class ChatMessagePacket implements Packet {

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
