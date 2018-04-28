package rocks.cleanstone.net.minecraft.login;

public class SessionServerResponse {

    private final String id, name;
    private final Property[] properties;

    public SessionServerResponse(String id, String name, Property[] properties) {
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

    public Property[] getProperties() {
        return properties;
    }

    public static class Property {

        private final String name, value, signature;

        public Property(String name, String value, String signature) {
            this.name = name;
            this.value = value;
            this.signature = signature;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public String getSignature() {
            return signature;
        }
    }
}
