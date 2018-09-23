package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

import java.io.IOException;

@Component
public class PlayerBlockPlacementCodec implements PacketCodec<PlayerBlockPlacementPacket> {

    @Override
    public PlayerBlockPlacementPacket decode(ByteBuf byteBuf) throws IOException {
        final Vector positionVector = ByteBufUtils.readVector(byteBuf);
        final int face = ByteBufUtils.readVarInt(byteBuf);
        final int hand = ByteBufUtils.readVarInt(byteBuf);

        final float cursorX = byteBuf.readFloat();
        final float cursorY = byteBuf.readFloat();
        final float cursorZ = byteBuf.readFloat();

        Position position = new Position(positionVector);

        return new PlayerBlockPlacementPacket(position, face, hand, cursorX, cursorY, cursorZ);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PlayerBlockPlacementPacket packet) {
        throw new UnsupportedOperationException("PlayerBlockPlacement is inbound and cannot be encoded");
    }
}
