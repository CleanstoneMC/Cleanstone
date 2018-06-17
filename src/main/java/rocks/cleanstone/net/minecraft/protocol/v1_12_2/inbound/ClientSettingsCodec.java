package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.ChatMode;
import rocks.cleanstone.net.packet.enums.DisplayedSkinParts;
import rocks.cleanstone.net.packet.enums.MainHand;
import rocks.cleanstone.net.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ClientSettingsCodec implements MinecraftPacketCodec {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {

        String locale = ByteBufUtils.readUTF8(byteBuf, 16);
        new Locale.Builder().setLanguageTag(locale.replace("_", "-")).build();

        final byte viewDistance = byteBuf.readByte();
        Preconditions.checkArgument(viewDistance >= 0, "viewDistance " + viewDistance + " is too small");

        int modeID = ByteBufUtils.readVarInt(byteBuf);
        ChatMode chatMode = ChatMode.fromModeID(modeID);
        Preconditions.checkNotNull(chatMode, "Invalid chatModeID " + modeID);

        final boolean chatColors = byteBuf.readBoolean();
        final DisplayedSkinParts[] displayedSkinParts = DisplayedSkinParts.fromBitMask(byteBuf.readByte());

        int handID = ByteBufUtils.readVarInt(byteBuf);
        MainHand mainHand = MainHand.fromHandID(handID);
        Preconditions.checkNotNull(mainHand, "Invalid mainHandID " + handID);

        return new ClientSettingsPacket(locale, viewDistance, chatMode, chatColors, displayedSkinParts, mainHand);
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

    @Override
    public int getProtocolPacketID() {
        return 0x04;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
