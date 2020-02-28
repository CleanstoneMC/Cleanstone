package rocks.cleanstone.endpoint.minecraft.vanilla.v1_12_2.net.protocol.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.UseItemPacket;
import rocks.cleanstone.game.inventory.Hand;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Codec
public class UseItemCodec implements InboundPacketCodec<UseItemPacket> {

    @Override
    public UseItemPacket decode(ByteBuf byteBuf) throws IOException {
        final int handID = ByteBufUtils.readVarInt(byteBuf);
        Hand hand = Hand.fromHandID(handID);
        Preconditions.checkNotNull(hand, "Invalid handID " + handID);

        return new UseItemPacket(hand);
    }
}
