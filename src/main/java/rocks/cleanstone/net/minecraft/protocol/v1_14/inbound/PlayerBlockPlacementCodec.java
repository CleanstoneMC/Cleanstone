package rocks.cleanstone.net.minecraft.protocol.v1_14.inbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

import java.io.IOException;

@Codec
public class PlayerBlockPlacementCodec implements InboundPacketCodec<PlayerBlockPlacementPacket> {

    @Override
    public PlayerBlockPlacementPacket decode(ByteBuf byteBuf) throws IOException {
        final int hand = ByteBufUtils.readVarInt(byteBuf);
        final Vector positionVector = ByteBufUtils.readVector(byteBuf, false);
        final int face = ByteBufUtils.readVarInt(byteBuf);

        final float cursorX = byteBuf.readFloat();
        final float cursorY = byteBuf.readFloat();
        final float cursorZ = byteBuf.readFloat();

        // player's head inside block? (unused)
        byteBuf.readBoolean();

        Position position = new Position(positionVector);

        return new PlayerBlockPlacementPacket(position, face, hand, cursorX, cursorY, cursorZ);
    }
}
