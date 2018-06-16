package rocks.cleanstone.game.world.data;

import io.netty.buffer.ByteBuf;

public interface WorldData {
    void write(ByteBuf buf);
}
