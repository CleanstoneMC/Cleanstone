package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.outbound.OutPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;

@Component
public class OutPlayerAbilitiesCodec implements OutboundPacketCodec<OutPlayerAbilitiesPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, OutPlayerAbilitiesPacket packet) {

        byteBuf.writeByte(PlayerAbility.toBitMask(packet.getPlayerAbilities()));
        byteBuf.writeFloat(packet.getFlyingSpeed());
        byteBuf.writeFloat(packet.getFieldOfViewModifier());

        return byteBuf;
    }
}
