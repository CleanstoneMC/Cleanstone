package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.net.minecraft.packet.inbound.UseItemPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class UseItemCodec implements PacketCodec<UseItemPacket> {

    @Override
    public UseItemPacket decode(ByteBuf byteBuf) throws IOException {
        final int handID = ByteBufUtils.readVarInt(byteBuf);
        Hand hand = Hand.fromHandID(handID);
        Preconditions.checkNotNull(hand, "Invalid handID " + handID);

        return new UseItemPacket(hand);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, UseItemPacket packet) {
        throw new UnsupportedOperationException("UseItem is inbound and cannot be encoded");
    }
}
