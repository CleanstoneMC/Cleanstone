package rocks.cleanstone.net.minecraft.entity.metadata.types;

public class VarInt implements EntityMetadataTypeInterface {

    public static VarInt of(int i) {
        return new VarInt();
    }
}
