package rocks.cleanstone.endpoint.minecraft.java.v1_12_2.net.protocol.outbound;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.VanillaEntity;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntityScheme;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.data.ProtocolEntitySchemeLoader;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.EntityConverterRegistry;
import rocks.cleanstone.endpoint.minecraft.java.net.entity.metadata.converter.EntityConverter;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.protocol.MinecraftClientProtocolLayer;
import rocks.cleanstone.game.entity.Entity;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Slf4j
@Codec
public class SpawnMobCodec implements OutboundPacketCodec<SpawnMobPacket> {

    private final ProtocolEntityScheme entityScheme;
    private final EntityConverterRegistry entityConverterRegistry;

    @Autowired
    public SpawnMobCodec(ProtocolEntitySchemeLoader entitySchemeLoader,
                         EntityConverterRegistry entityConverterRegistry) throws IOException {
        entityScheme = entitySchemeLoader.loadData(MinecraftClientProtocolLayer.MINECRAFT_V1_13_2);
        this.entityConverterRegistry = entityConverterRegistry;
    }

    public SpawnMobCodec(ProtocolEntityScheme entityScheme, EntityConverterRegistry entityConverterRegistry) {
        this.entityScheme = entityScheme;
        this.entityConverterRegistry = entityConverterRegistry;
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, SpawnMobPacket packet) {
        Entity entity = packet.getEntity();
        EntityConverter entityConverter = entityConverterRegistry.getConverter(entity.getClass());
        if (entityConverter == null) {
            throw new IllegalArgumentException("Cannot find converter for " + entity.getClass());
        }
        //noinspection unchecked
        VanillaEntity vanillaEntity = entityConverter.convert(entity, entityScheme);

        ByteBufUtils.writeVarInt(byteBuf, packet.getEntityID());
        ByteBufUtils.writeUUID(byteBuf, packet.getEntityUUID());
        ByteBufUtils.writeVarInt(byteBuf, vanillaEntity.getEntityTypeID());
        byteBuf.writeDouble(packet.getPosition().getX());
        byteBuf.writeDouble(packet.getPosition().getY());
        byteBuf.writeDouble(packet.getPosition().getZ());
        byteBuf.writeByte((int) packet.getPosition().getRotation().getYaw());
        byteBuf.writeByte((int) packet.getPosition().getRotation().getPitch());
        byteBuf.writeByte((int) packet.getPosition().getHeadRotation().getPitch());
        // TODO add velocity
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);
        byteBuf.writeShort(0);

        vanillaEntity.getEntityMetadata().getMetadataEntries().forEach((entityMetadataEntry -> {
            byteBuf.writeByte(entityMetadataEntry.getIndex());
            ByteBufUtils.writeVarInt(byteBuf, entityMetadataEntry.getType().getTypeID());
            ByteBuf valueData = entityMetadataEntry.getData().serialize();
            byteBuf.writeBytes(valueData);
            valueData.release();
        }));
        // Terminating metadata entry
        byteBuf.writeByte(0xff);

        return byteBuf;
    }
}
