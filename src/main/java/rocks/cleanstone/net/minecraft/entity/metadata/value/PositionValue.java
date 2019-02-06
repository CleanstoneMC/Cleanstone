package rocks.cleanstone.net.minecraft.entity.metadata.value;

import com.google.common.base.Preconditions;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PositionValue implements EntityMetadataEntryValue {

    private final Position position;

    private PositionValue(Position position) {
        Preconditions.checkNotNull(position, "position cannot be null");
        this.position = position;
    }

    public static PositionValue of(Position position) {
        return new PositionValue(position);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        ByteBufUtils.writeVector(byteBuf, position.toVector());
        return byteBuf;
    }
}
