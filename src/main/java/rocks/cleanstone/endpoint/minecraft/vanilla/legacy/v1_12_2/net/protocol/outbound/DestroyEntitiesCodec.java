package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Codec
public class DestroyEntitiesCodec implements OutboundPacketCodec<DestroyEntitiesPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, DestroyEntitiesPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityIDs().size());
        for (int entityID : packet.getEntityIDs()) {
            ByteBufUtils.writeVarInt(byteBuf, entityID);
        }
        return byteBuf;
    }
}
