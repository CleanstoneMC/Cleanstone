package rocks.cleanstone.core.player;

import java.util.UUID;

public class SimplePlayerID implements PlayerID {
    private final UUID uuid;
    private String accountName;
    private String name;

    public SimplePlayerID(UUID uuid, String accountName, String name) {
        this.uuid = uuid;
        this.accountName = accountName;
        this.name = name;
    }

    public SimplePlayerID(UUID uuid, String accountName) {
        this(uuid, accountName, accountName);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
