package rocks.cleanstone.net.minecraft.entity.metadata.types;

public class Boolean implements EntityMetadataTypeInterface {

    public static Boolean of(boolean bool) {
        return new Boolean();
    }
}
