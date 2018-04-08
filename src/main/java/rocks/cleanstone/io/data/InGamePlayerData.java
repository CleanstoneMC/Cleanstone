package rocks.cleanstone.io.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class InGamePlayerData {
    @Id
    private UUID uuid;
    private int gameMode;

    protected InGamePlayerData() {
    }

    public InGamePlayerData(UUID uuid, int gameMode) {
        this.uuid = uuid;
        this.gameMode = gameMode;
    }

    @Override
    public String toString() {
        return String.format("InGamePlayerData[uuid=%s, gameMode='%s']",
                uuid, gameMode);
    }
}
