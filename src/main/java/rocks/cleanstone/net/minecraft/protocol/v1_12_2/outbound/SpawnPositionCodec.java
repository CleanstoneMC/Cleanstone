package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnPositionPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class SpawnPositionCodec implements OutboundPacketCodec<SpawnPositionPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SpawnPositionPacket packet) {

        ByteBufUtils.writeVector(byteBuf, packet.getPosition().toVector(), true);

        return byteBuf;
    }
}
