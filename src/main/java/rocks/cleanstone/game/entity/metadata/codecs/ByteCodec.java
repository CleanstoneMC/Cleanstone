package rocks.cleanstone.game.entity.metadata.codecs;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.game.entity.metadata.type.MetadataType;
import rocks.cleanstone.game.entity.metadata.type.VanillaMetadataType;
import rocks.cleanstone.net.utils.ByteBufUtils;

public class ByteCodec implements MetadataCodec<java.lang.Byte> {

    private static final MetadataType metadataType = VanillaMetadataType.BYTE;

    @Override
    public ByteBuf encode(ByteBuf byteBuf, java.lang.Byte metadata) {

        ByteBufUtils.writeVarInt(byteBuf, metadataType.getTypeID());
        byteBuf.writeByte(metadata);

        return byteBuf;
    }

    @Override
    public java.lang.Byte decode(ByteBuf byteBuf) {
        return byteBuf.readByte();
    }
}
