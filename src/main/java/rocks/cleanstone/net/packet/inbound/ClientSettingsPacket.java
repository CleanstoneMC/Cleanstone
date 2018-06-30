package rocks.cleanstone.net.packet.inbound;

import java.util.Collection;
import java.util.Locale;

import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.game.chat.ChatMode;
import rocks.cleanstone.net.packet.enums.DisplayedSkinPart;
import rocks.cleanstone.game.inventory.MainHandSide;

public class ClientSettingsPacket implements Packet {

    private final Locale locale;
    private final int viewDistance;
    private final ChatMode chatMode;
    private final boolean chatColors;
    private final Collection<DisplayedSkinPart> displayedSkinParts;
    private final MainHandSide mainHandSide;

    public ClientSettingsPacket(Locale locale, int viewDistance, int chatMode, boolean chatColors, int displayedSkinParts, int mainHand) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = ChatMode.fromModeID(chatMode);
        this.chatColors = chatColors;
        this.displayedSkinParts = DisplayedSkinPart.fromBitMask(displayedSkinParts);
        this.mainHandSide = MainHandSide.fromHandID(mainHand);
    }

    public ClientSettingsPacket(Locale locale, int viewDistance, ChatMode chatMode, boolean chatColors,
                                Collection<DisplayedSkinPart> displayedSkinParts, MainHandSide mainHandSide) {
        this.locale = locale;
        this.viewDistance = viewDistance;
        this.chatMode = chatMode;
        this.chatColors = chatColors;
        this.displayedSkinParts = displayedSkinParts;
        this.mainHandSide = mainHandSide;
    }

    public Locale getLocale() {
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

    public Collection<DisplayedSkinPart> getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    public MainHandSide getMainHandSide() {
        return mainHandSide;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.CLIENT_SETTINGS;
    }
}
