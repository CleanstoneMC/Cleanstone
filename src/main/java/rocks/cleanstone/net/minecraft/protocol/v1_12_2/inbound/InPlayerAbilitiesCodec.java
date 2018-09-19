package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.protocol.PacketCodec;

public class InPlayerAbilitiesCodec implements PacketCodec {

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
}
