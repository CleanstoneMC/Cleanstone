package rocks.cleanstone.net.packet.minecraft.inbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftInboundPacketType;

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
