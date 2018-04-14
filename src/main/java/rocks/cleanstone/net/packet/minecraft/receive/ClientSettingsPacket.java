package rocks.cleanstone.net.packet.minecraft.receive;

import rocks.cleanstone.net.packet.PacketDirection;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.StandardPacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftPacket;
import rocks.cleanstone.net.packet.minecraft.enums.ChatMode;
import rocks.cleanstone.net.packet.minecraft.enums.DisplayedSkinParts;
import rocks.cleanstone.net.packet.minecraft.enums.MainHand;

public class ClientSettingsPacket implements MinecraftPacket {

    private final String locale;
    private final int viewDistance;
    private final ChatMode chatMode;
    private final boolean chatColors;
    private final DisplayedSkinParts[] displayedSkinParts;
    private final MainHand mainHand;

    public ClientSettingsPacket(String locale, int viewDistance, int chatMode, boolean chatColors, int displayedSkinParts, int mainHand) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = ChatMode.fromModeID(chatMode);
        this.chatColors = chatColors;
        this.displayedSkinParts = DisplayedSkinParts.fromBitMask(displayedSkinParts);
        this.mainHand = MainHand.fromHandID(mainHand);
    }

    public ClientSettingsPacket(String locale, int viewDistance, ChatMode chatMode, boolean chatColors, DisplayedSkinParts[] displayedSkinParts, MainHand mainHand) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = chatMode;
        this.chatColors = chatColors;
        this.displayedSkinParts = displayedSkinParts;
        this.mainHand = mainHand;
    }

    @Override
    public PacketType getType() {
        return StandardPacketType.MINECRAFT;
    }

    @Override
    public PacketDirection getDirection() {
        return PacketDirection.RECEIVE;
    }
}
