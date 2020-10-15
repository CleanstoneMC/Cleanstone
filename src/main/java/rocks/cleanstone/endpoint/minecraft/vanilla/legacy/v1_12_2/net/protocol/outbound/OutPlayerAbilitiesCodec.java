package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.enums.PlayerAbility;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Codec
public class OutPlayerAbilitiesCodec implements OutboundPacketCodec<OutPlayerAbilitiesPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutPlayerAbilitiesPacket packet) {

        byteBuf.writeByte(PlayerAbility.toBitMask(packet.getPlayerAbilities()));
        byteBuf.writeFloat(packet.getFlyingSpeed());
        byteBuf.writeFloat(packet.getFieldOfViewModifier());

        return byteBuf;
    }
}
