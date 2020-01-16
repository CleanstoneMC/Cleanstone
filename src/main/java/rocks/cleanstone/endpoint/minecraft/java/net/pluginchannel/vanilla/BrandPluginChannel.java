package rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel.vanilla;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.java.net.pluginchannel.PluginChannel;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Component
public class BrandPluginChannel implements PluginChannel<BrandMessage> {

    @Override
    public String getName() {
        return "minecraft:brand";
    }

    @Override
    public BrandMessage decode(ByteBuf byteBuf) throws IOException {
        final String brand = ByteBufUtils.readUTF8(byteBuf);

        return new BrandMessage(brand);
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, BrandMessage pluginMessage) throws IOException {
        ByteBufUtils.writeUTF8(byteBuf, pluginMessage.getBrand());

        return byteBuf;
    }
}
