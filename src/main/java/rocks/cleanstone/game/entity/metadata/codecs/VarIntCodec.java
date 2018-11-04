package rocks.cleanstone.game.entity.metadata.codecs;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.entity.metadata.type.MetadataType;
import rocks.cleanstone.game.entity.metadata.type.VanillaMetadataType;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

public class VarIntCodec implements MetadataCodec<Integer> {

    private static final MetadataType metadataType = VanillaMetadataType.VAR_INT;

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Integer metadata) {

        ByteBufUtils.writeVarInt(byteBuf, metadataType.getTypeID());
        ByteBufUtils.writeVarInt(byteBuf, metadata);

        return byteBuf;
    }

    @Override
    public Integer decode(ByteBuf byteBuf) throws IOException {
        return ByteBufUtils.readVarInt(byteBuf);
    }
}
