package rocks.cleanstone.net.minecraft.packet.data;

public class Text { // TODO
    private final String json;

    private Text(String json) {
        this.json = json;
    }

    public static Text fromPlain(String plainText) {
        return new Text("{text\":\"" + plainText + "\"}");
    }

    @Override
    public String toString() {
        return json;
    }
}
