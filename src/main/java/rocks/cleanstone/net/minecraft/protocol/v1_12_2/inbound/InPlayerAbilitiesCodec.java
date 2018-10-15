package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;

@Component
public class InPlayerAbilitiesCodec implements InboundPacketCodec<InPlayerAbilitiesPacket> {

    @Override
    public InPlayerAbilitiesPacket decode(ByteBuf byteBuf) {
        final PlayerAbility[] playerAbilities = PlayerAbility.fromBitMask(byteBuf.readByte());
        final float flyingSpeed = byteBuf.readFloat();
        final float walkingSpeed = byteBuf.readFloat();

        return new InPlayerAbilitiesPacket(playerAbilities, flyingSpeed, walkingSpeed);
    }
}
