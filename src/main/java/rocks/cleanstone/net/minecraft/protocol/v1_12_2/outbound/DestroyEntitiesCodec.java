package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class DestroyEntitiesCodec implements OutboundPacketCodec<DestroyEntitiesPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, DestroyEntitiesPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityIDs().size());
        for (final int entityID : packet.getEntityIDs()) {
            ByteBufUtils.writeVarInt(byteBuf, entityID);
        }
        return byteBuf;
    }
}
