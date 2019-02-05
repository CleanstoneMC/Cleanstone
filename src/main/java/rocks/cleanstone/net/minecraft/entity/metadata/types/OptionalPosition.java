package rocks.cleanstone.net.minecraft.entity.metadata.types;

public class OptionalPosition implements EntityMetadataTypeInterface {

    public static OptionalPosition of() {
        return new OptionalPosition();
    }
}
