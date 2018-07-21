package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.ProtocolState;

public class OutPlayerAbilitiesCodec implements MinecraftPacketCodec {

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

    @Override
    public int getProtocolPacketID() {
        return 0x2E;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
