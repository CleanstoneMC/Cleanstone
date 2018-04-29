package rocks.cleanstone.net.minecraft.packet.data;

public class Chat { // TODO
    private final String json;

    public Chat(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return json;
    }
}
