package rocks.cleanstone.net.minecraft.packet.outbound;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.minecraft.packet.enums.ChatPosition;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class OutChatMessagePacket implements Packet {

    private final Chat chat;
    private final ChatPosition chatPosition;

    public OutChatMessagePacket(Chat chat, ChatPosition chatPosition) {
        this.chat = chat;
        this.chatPosition = chatPosition;
    }

    public Chat getChat() {
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
