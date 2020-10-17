package rocks.cleanstone.endpoint.minecraft.vanilla.legacy.state.mapping;

import java.util.Objects;

public class IDMetadataPair {
    private final Integer id;
    private final Integer metadata;

    public IDMetadataPair(Integer id, Integer metadata) {
        this.id = id;
        this.metadata = metadata;
    }

    public static IDMetadataPair of(int id, int metadata) {
        return new IDMetadataPair(id, metadata);
    }

    public static IDMetadataPair decode(int encoded) {
        return of(encoded >> 4, encoded & 0xF);
    }

    public Integer getID() {
        return id;
    }

    public Integer getMetadata() {
        return metadata;
    }

    public Integer encode() {
        return id << 4 | (metadata & 0xF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IDMetadataPair that = (IDMetadataPair) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, metadata);
    }

    @Override
    public String toString() {
        return "IDMetadataPair{" +
                "id=" + id +
                ", metadata=" + metadata +
                '}';
    }
}
