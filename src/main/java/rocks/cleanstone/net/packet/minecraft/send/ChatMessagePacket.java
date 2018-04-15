package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.ChatPosition;

public class ChatMessagePacket extends SendPacket {

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
        return MinecraftSendPacketType.CHAT_MESSAGE;
    }
}
