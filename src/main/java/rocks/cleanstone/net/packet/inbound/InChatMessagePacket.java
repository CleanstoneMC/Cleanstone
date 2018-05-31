package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InChatMessagePacket implements Packet {

    private final String message;

    public InChatMessagePacket(String message) {
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
