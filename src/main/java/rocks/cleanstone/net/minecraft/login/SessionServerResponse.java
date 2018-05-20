package rocks.cleanstone.net.minecraft.login;

import rocks.cleanstone.player.UserProperty;

public class SessionServerResponse {

    private final String id, name;
    private final UserProperty[] properties;

    public SessionServerResponse(String id, String name, UserProperty[] properties) {
        this.id = id;
        this.name = name;
        this.properties = properties;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserProperty[] getProperties() {
        return properties;
    }
}
