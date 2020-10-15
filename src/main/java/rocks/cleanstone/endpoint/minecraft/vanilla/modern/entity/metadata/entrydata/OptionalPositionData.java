package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.entrydata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.Position;

import javax.annotation.Nullable;

public class OptionalPositionData implements EntityMetadataEntryData {
    @Nullable
    private final Position position;

    private OptionalPositionData(@Nullable Position position) {
        this.position = position;
    }

    public static OptionalPositionData of(@Nullable Position position) {
        return new OptionalPositionData(position);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBoolean(position != null);
        if (position != null) {
            ByteBuf positionData = PositionData.of(position).serialize();
            byteBuf.writeBytes(positionData);
            positionData.release();
        }
        return byteBuf;
    }
}
