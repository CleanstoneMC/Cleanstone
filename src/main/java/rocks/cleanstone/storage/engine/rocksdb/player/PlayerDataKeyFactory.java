package rocks.cleanstone.storage.engine.rocksdb.player;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.data.PlayerDataType;

public class PlayerDataKeyFactory {
    private PlayerDataKeyFactory() {
    }

    public static ByteBuf create(Player player, PlayerDataType type) {
        ByteBuf buf = Unpooled.buffer();
        ByteBufUtils.writeUUID(buf, player.getID().getUUID());
        ByteBufUtils.writeVarInt(buf, type.getTypeID());
        return buf;
    }
}
