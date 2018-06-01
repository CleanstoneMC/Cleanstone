package rocks.cleanstone.net.packet.data;

public class Text { // TODO
    private final String json;

    private Text(String json) {
        this.json = json;
    }

    public static Text fromPlain(String plainText) {
        return new Text("{\"text\":\"" + plainText + "\"}");
    }

    public static Text of(String text) {
        // TODO translate colors
        return new Text("{\"text\":\"" + text + "\"}");
    }

    @Override
    public String toString() {
        return json;
    }
}
