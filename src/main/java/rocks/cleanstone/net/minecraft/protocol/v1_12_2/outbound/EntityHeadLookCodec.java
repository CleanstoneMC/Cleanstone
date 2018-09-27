package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.EntityHeadLookPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class EntityHeadLookCodec implements OutboundPacketCodec<EntityHeadLookPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, EntityHeadLookPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        byteBuf.writeByte(packet.getYaw());

        return byteBuf;
    }
}
