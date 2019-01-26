package rocks.cleanstone.net.minecraft.entity;

public class ProtocolEntitiesData {

    private final ProtocolEntityData[] entities;

    public ProtocolEntitiesData(ProtocolEntityData[] entities) {
        this.entities = entities;
    }

    public ProtocolEntityData[] getEntities() {
        return entities;
    }
}
