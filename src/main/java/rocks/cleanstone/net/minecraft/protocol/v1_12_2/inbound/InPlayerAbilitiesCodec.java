package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Codec
public class InPlayerAbilitiesCodec implements InboundPacketCodec<InPlayerAbilitiesPacket> {

    @Override
    public InPlayerAbilitiesPacket decode(ByteBuf byteBuf) {
        PlayerAbility[] playerAbilities = PlayerAbility.fromBitMask(byteBuf.readByte());
        float flyingSpeed = byteBuf.readFloat(), walkingSpeed = byteBuf.readFloat();

        return new InPlayerAbilitiesPacket(playerAbilities, flyingSpeed, walkingSpeed);
    }
}
