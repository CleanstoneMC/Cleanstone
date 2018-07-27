package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.DiggingStatus;
import rocks.cleanstone.net.packet.inbound.PlayerDiggingPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.utils.Vector;

import java.io.IOException;

public class PlayerDiggingCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) throws IOException {
        final int diggingStatusID = ByteBufUtils.readVarInt(byteBuf);
        final Vector positionVector = ByteBufUtils.readVector(byteBuf);
        final byte faceID = byteBuf.readByte();

        final DiggingStatus diggingStatus = DiggingStatus.fromStatusID(diggingStatusID);
        Preconditions.checkNotNull(diggingStatus, "Invalid diggingStatusID " + diggingStatusID);

        final Position position = new Position(positionVector);
        final Face face = Face.fromFaceID(faceID);
        Preconditions.checkNotNull(face, "Invalid faceID " + faceID);

        return new PlayerDiggingPacket(diggingStatus, position, face);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        throw new UnsupportedOperationException("PlayerDigging is inbound and cannot be encoded");
    }

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
