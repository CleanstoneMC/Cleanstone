package rocks.cleanstone.net.minecraft.status;

public class StatusResponse {

    private final Version version;
    private final Players players;
    private final Description description;
    private final String favicon;

    public StatusResponse(Version version, Players players, Description description, String favicon) {
        this.version = version;
        this.players = players;
        this.description = description;
        this.favicon = favicon;
    }

    public StatusResponse(Version version, Players players, Description description) {
        this(version, players, description, null);
    }

    public static class Version {
        private final String name;
        private final int protocol;

        public Version(String name, int protocol) {
            this.name = name;
            this.protocol = protocol;
        }
    }

    public static class Players {
        private final int max, online;
        private final SampleItem[] sample;

        public Players(int max, int online, SampleItem[] sample) {
            this.max = max;
            this.online = online;
            this.sample = sample;
        }

        public static class SampleItem {
            private final String name, id;

            public SampleItem(String name, String id) {
                this.name = name;
                this.id = id;
            }
        }
    }

    public static class Description {
        private final String text;

        public Description(String text) {
            this.text = text;
        }
    }
}
