package rocks.cleanstone.game.entity.metadata;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import rocks.cleanstone.data.InOutCodec;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.game.entity.metadata.type.MetadataType;

import java.io.IOException;

public class MetadataEntityCodec implements InOutCodec<Entity, ByteBuf> {

    public Entity decode(ByteBuf data) throws IOException {
        // TODO
        return null;
    }

    public ByteBuf encode(Entity entity) throws IOException {

        ByteBuf byteBuf = Unpooled.buffer();
        for (int i = 0; i < entity.getMetadata().getMetadataList().size(); i++) {
            MetadataType metadataEntry = entity.getMetadata().getMetadataList().get(i);

            byteBuf.writeByte(i);


        }

        // TODO
        return null;
    }
}
