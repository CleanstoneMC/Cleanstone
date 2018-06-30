package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.game.chat.ChatPosition;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutChatMessagePacket implements Packet {

    private final Text chat;
    private final ChatPosition chatPosition;

    public OutChatMessagePacket(Text chat, ChatPosition chatPosition) {
        this.chat = chat;
        this.chatPosition = chatPosition;
    }

    public Text getChat() {
        return chat;
    }

    public ChatPosition getChatPosition() {
        return chatPosition;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHAT_MESSAGE;
    }
}
