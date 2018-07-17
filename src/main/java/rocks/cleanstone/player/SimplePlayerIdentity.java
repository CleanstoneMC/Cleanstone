package rocks.cleanstone.player;

import com.google.common.base.Objects;

import java.util.UUID;

import rocks.cleanstone.game.Identity;

public class SimplePlayerIdentity implements Identity {

    private final UUID uuid;
    private String accountName;
    private String name;

    public SimplePlayerIdentity(UUID uuid, String accountName, String name) {
        this.uuid = uuid;
        this.accountName = accountName;
        this.name = name;
    }

    public SimplePlayerIdentity(UUID uuid, String accountName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimplePlayerIdentity)) return false;
        SimplePlayerIdentity that = (SimplePlayerIdentity) o;
        return Objects.equal(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}
