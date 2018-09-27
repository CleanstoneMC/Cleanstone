package rocks.cleanstone.net.minecraft.pluginchannel.vanilla;

import rocks.cleanstone.net.minecraft.pluginchannel.PluginChannel;

public class BrandMessage implements PluginChannel.PluginMessage {
    private final String brand;

    BrandMessage(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
