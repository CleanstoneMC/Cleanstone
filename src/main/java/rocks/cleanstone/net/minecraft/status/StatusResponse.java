package rocks.cleanstone.net.minecraft.status;

public class StatusResponse {

    public StatusResponse(Version version, Players players, Description description, String favicon) {
        Version version1 = version;
        Players players1 = players;
        Description description1 = description;
        String favicon1 = favicon;
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
