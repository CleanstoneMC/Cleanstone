package rocks.cleanstone.game.entity.metadata;

import rocks.cleanstone.game.entity.metadata.type.MetadataType;

import java.util.ArrayList;
import java.util.List;

public class Metadata {
    private final List<MetadataType> metadataList = new ArrayList<>();
    //TODO

    public List<MetadataType> getMetadataList() {
        return metadataList;
    }
}
