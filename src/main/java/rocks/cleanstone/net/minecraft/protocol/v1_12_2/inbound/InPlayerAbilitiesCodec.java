package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.net.minecraft.packet.inbound.InPlayerAbilitiesPacket;
import rocks.cleanstone.net.protocol.PacketCodec;

@Component
public class InPlayerAbilitiesCodec implements PacketCodec<InPlayerAbilitiesPacket> {

    @Override
    public InPlayerAbilitiesPacket decode(ByteBuf byteBuf) {
        PlayerAbility[] playerAbilities = PlayerAbility.fromBitMask(byteBuf.readByte());
        float flyingSpeed = byteBuf.readFloat(), walkingSpeed = byteBuf.readFloat();

        return new InPlayerAbilitiesPacket(playerAbilities, flyingSpeed, walkingSpeed);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, InPlayerAbilitiesPacket packet) {
        throw new UnsupportedOperationException("InPlayerAbilities is inbound and cannot be encoded");
    }
}
