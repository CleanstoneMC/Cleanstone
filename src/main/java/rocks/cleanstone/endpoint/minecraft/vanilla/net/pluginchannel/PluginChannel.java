package rocks.cleanstone.endpoint.minecraft.vanilla.net.pluginchannel;

import io.netty.buffer.ByteBuf;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public interface PluginChannel<T extends PluginChannel.PluginMessage> {
    @NotNull
    String getName();

    T decode(ByteBuf byteBuf) throws IOException;

    ByteBuf encode(ByteBuf byteBuf, T pluginMessage) throws IOException;

    interface PluginMessage {
    }
}
