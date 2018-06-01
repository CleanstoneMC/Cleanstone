package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.enums.ChatMode;
import rocks.cleanstone.net.packet.enums.DisplayedSkinParts;
import rocks.cleanstone.net.packet.enums.MainHand;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ClientSettingsPacket implements Packet {

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

    public String getLocale() {
        return locale;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public ChatMode getChatMode() {
        return chatMode;
    }

    public boolean isChatColors() {
        return chatColors;
    }

    public DisplayedSkinParts[] getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    public MainHand getMainHand() {
        return mainHand;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CLIENT_SETTINGS;
    }
}
