package rocks.cleanstone.net.minecraft.entity.metadata.types;

public class OptionalChat implements EntityMetadataTypeInterface {

    public static OptionalChat of() {
        return new OptionalChat();
    }
}
