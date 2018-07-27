package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.game.chat.ChatMode;
import rocks.cleanstone.game.inventory.MainHandSide;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.DisplayedSkinPart;
import rocks.cleanstone.net.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

public class ClientSettingsCodec implements PacketCodec {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {

        String localeID = ByteBufUtils.readUTF8(byteBuf, 16);
        Locale locale = new Locale.Builder().setLanguageTag(localeID.replace("_", "-")).build();

        final byte viewDistance = byteBuf.readByte();
        Preconditions.checkArgument(viewDistance >= 2, "viewDistance " + viewDistance + " is too small");

        int modeID = ByteBufUtils.readVarInt(byteBuf);
        ChatMode chatMode = ChatMode.fromModeID(modeID);
        Preconditions.checkNotNull(chatMode, "Invalid chatModeID " + modeID);

        final boolean chatColors = byteBuf.readBoolean();
        final Collection<DisplayedSkinPart> displayedSkinParts = DisplayedSkinPart.fromBitMask(byteBuf.readByte());

        int handID = ByteBufUtils.readVarInt(byteBuf);
        MainHandSide mainHandSide = MainHandSide.fromHandID(handID);
        Preconditions.checkNotNull(mainHandSide, "Invalid mainHandID " + handID);

        return new ClientSettingsPacket(locale, viewDistance, chatMode, chatColors, displayedSkinParts, mainHandSide);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("ClientSettings is inbound and cannot be encoded");
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
