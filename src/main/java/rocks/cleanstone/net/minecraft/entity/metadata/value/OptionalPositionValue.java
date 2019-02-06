package rocks.cleanstone.net.minecraft.entity.metadata.value;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.Position;

public class OptionalPositionValue implements EntityMetadataEntryValue {
    @Nullable
    private final Position position;

    private OptionalPositionValue(@Nullable Position position) {
        this.position = position;
    }

    public static OptionalPositionValue of(@Nullable Position position) {
        return new OptionalPositionValue(position);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBoolean(position != null);
        if (position != null) {
            ByteBuf positionData = PositionValue.of(position).serialize();
            byteBuf.writeBytes(positionData);
            positionData.release();
        }
        return byteBuf;
    }
}
