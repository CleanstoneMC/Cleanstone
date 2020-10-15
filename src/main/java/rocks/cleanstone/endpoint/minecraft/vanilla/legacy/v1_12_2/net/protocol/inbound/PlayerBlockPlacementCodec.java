package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.v1_12_2.net.protocol.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

import java.io.IOException;

@Codec
public class PlayerBlockPlacementCodec implements InboundPacketCodec<PlayerBlockPlacementPacket> {

    @Override
    public PlayerBlockPlacementPacket decode(ByteBuf byteBuf) throws IOException {
        final Vector positionVector = ByteBufUtils.readVector(byteBuf, true);
        final int face = ByteBufUtils.readVarInt(byteBuf);
        final int hand = ByteBufUtils.readVarInt(byteBuf);

        final float cursorX = byteBuf.readFloat();
        final float cursorY = byteBuf.readFloat();
        final float cursorZ = byteBuf.readFloat();

        Position position = new Position(positionVector);

        return new PlayerBlockPlacementPacket(position, face, hand, cursorX, cursorY, cursorZ);
    }
}
