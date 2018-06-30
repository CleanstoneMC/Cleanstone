package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.protocol.MinecraftPacketCodec;
import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.net.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.ProtocolState;

public class InPlayerAbilitiesCodec implements MinecraftPacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        PlayerAbility[] playerAbilities = PlayerAbility.fromBitMask(byteBuf.readByte());
        float flyingSpeed = byteBuf.readFloat(), walkingSpeed = byteBuf.readFloat();

        return new InPlayerAbilitiesPacket(playerAbilities, flyingSpeed, walkingSpeed);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("InPlayerAbilities is inbound and cannot be encoded");
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
        return 0x13;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.PLAY;
    }
}
