package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.enums.ChatMode;
import rocks.cleanstone.net.minecraft.packet.enums.DisplayedSkinParts;
import rocks.cleanstone.net.minecraft.packet.enums.MainHand;
import rocks.cleanstone.net.minecraft.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class ClientSettingsCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        String locale;

        try {
            locale = ByteBufUtils.readUTF8(byteBuf);
        } catch (IOException e) {
            e.printStackTrace();
            locale = "en_US";
        }

        final byte viewDistance = byteBuf.readByte();

        ChatMode chatMode;

        try {
            chatMode = ChatMode.fromModeID(ByteBufUtils.readVarInt(byteBuf));
        } catch (IOException e) {
            e.printStackTrace();
            chatMode = ChatMode.ENABLED;
        }

        final boolean chatColors = byteBuf.readBoolean();
        final DisplayedSkinParts[] displayedSkinParts = DisplayedSkinParts.fromBitMask(byteBuf.readByte());

        MainHand mainHand;
        try {
            mainHand = MainHand.fromHandID(ByteBufUtils.readVarInt(byteBuf));
        } catch (IOException e) {
            e.printStackTrace();
            mainHand = MainHand.RIGHT;
        }

        return new ClientSettingsPacket(locale, viewDistance, chatMode, chatColors, displayedSkinParts, mainHand);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("Ping is inbound and cannot be encoded");
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
