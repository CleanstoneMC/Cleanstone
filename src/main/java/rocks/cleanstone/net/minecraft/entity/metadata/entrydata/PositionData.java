package rocks.cleanstone.net.minecraft.entity.metadata.entrydata;

import com.google.common.base.Preconditions;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class PositionData implements EntityMetadataEntryData {

    private final Position position;

    private PositionData(Position position) {
        Preconditions.checkNotNull(position, "position cannot be null");
        this.position = position;
    }

    public static PositionData of(Position position) {
        return new PositionData(position);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        ByteBufUtils.writeVector(byteBuf, position.toVector());
        return byteBuf;
    }
}
