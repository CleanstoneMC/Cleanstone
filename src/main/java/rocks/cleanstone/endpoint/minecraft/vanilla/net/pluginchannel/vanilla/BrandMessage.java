package rocks.cleanstone.endpoint.minecraft.vanilla.net.pluginchannel.vanilla;

import rocks.cleanstone.endpoint.minecraft.vanilla.net.pluginchannel.PluginChannel;

public class BrandMessage implements PluginChannel.PluginMessage {
    private final String brand;

    public BrandMessage(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
