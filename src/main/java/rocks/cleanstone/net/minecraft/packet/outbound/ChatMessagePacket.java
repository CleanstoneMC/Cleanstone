package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.ChatPosition;

public class ChatMessagePacket implements Packet {

    private final String jsonData;
    private final ChatPosition chatPosition;

    public ChatMessagePacket(String jsonData, byte chatPosition) {
        this.jsonData = jsonData;
        this.chatPosition = ChatPosition.fromPositionID(chatPosition);
    }

    public ChatMessagePacket(String jsonData, ChatPosition chatPosition) {
        this.jsonData = jsonData;
        this.chatPosition = chatPosition;
    }

    public String getJsonData() {
        return jsonData;
    }

    public ChatPosition getChatPosition() {
        return chatPosition;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHAT_MESSAGE;
    }
}
