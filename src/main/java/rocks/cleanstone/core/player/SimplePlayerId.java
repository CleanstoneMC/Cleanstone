package rocks.cleanstone.core.player;

import java.util.UUID;

public class SimplePlayerId implements PlayerId {
    private final UUID uuid;
    private String accountName;
    private String name;

    public SimplePlayerId(UUID uuid, String accountName, String name) {
        this.uuid = uuid;
        this.accountName = accountName;
        this.name = name;
    }

    public SimplePlayerId(UUID uuid, String accountName) {
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
