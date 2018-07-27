package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

public class OutPlayerAbilitiesCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("PlayerAbilities is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        OutPlayerAbilitiesPacket playerAbilitiesPacket = (OutPlayerAbilitiesPacket) packet;

        byteBuf.writeByte(PlayerAbility.toBitMask(playerAbilitiesPacket.getPlayerAbilities()));
        byteBuf.writeFloat(playerAbilitiesPacket.getFlyingSpeed());
        byteBuf.writeFloat(playerAbilitiesPacket.getFieldOfViewModifier());

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
}
