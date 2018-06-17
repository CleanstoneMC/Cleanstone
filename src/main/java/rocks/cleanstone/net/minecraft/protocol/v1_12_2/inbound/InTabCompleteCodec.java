package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.inbound.InTabCompletePacket;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

public class InTabCompleteCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final String text = ByteBufUtils.readUTF8(byteBuf);

        final boolean assumeCommand = byteBuf.readBoolean();
        final boolean hasPosition = byteBuf.readBoolean();
        final Vector lookedAtBlock;

        if (hasPosition) {
            lookedAtBlock = ByteBufUtils.readVector(byteBuf);
        } else {
            lookedAtBlock = null;
        }


        return new InTabCompletePacket(text, assumeCommand, hasPosition, lookedAtBlock);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("TabCompletion is inbound and cannot be encoded");
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
        return 0x01;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
