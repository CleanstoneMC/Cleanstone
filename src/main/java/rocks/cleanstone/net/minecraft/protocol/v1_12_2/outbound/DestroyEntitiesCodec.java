package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Component
public class DestroyEntitiesCodec implements PacketCodec<DestroyEntitiesPacket> {

    @Override
    public DestroyEntitiesPacket decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("DestroyEntities is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, DestroyEntitiesPacket packet) {

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityIDs().size());
        for (int entityID : packet.getEntityIDs()) {
            ByteBufUtils.writeVarInt(byteBuf, entityID);
        }
        return byteBuf;
    }
}
