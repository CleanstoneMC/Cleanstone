package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.outbound.DestroyEntitiesPacket;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.ProtocolState;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class DestroyEntitiesCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("DestroyEntities is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        DestroyEntitiesPacket destroyEntitiesPacket = (DestroyEntitiesPacket) packet;

        ByteBufUtils.writeVarInt(byteBuf, destroyEntitiesPacket.getEntityIDs().size());
        for (int entityID : destroyEntitiesPacket.getEntityIDs()) {
            ByteBufUtils.writeVarInt(byteBuf, entityID);
        }
        return byteBuf;
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
        return 0x32;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
