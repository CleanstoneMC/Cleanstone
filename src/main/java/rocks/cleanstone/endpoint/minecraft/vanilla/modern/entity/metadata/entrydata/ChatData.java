package rocks.cleanstone.endpoint.minecraft.vanilla.modern.entity.metadata.entrydata;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;

@Slf4j
public class ChatData implements EntityMetadataEntryData {

    private final Text text;

    private ChatData(Text text) {
        Preconditions.checkNotNull(text, "text cannot be null");
        this.text = text;
    }

    public static ChatData of(Text text) {
        return new ChatData(text);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            ByteBufUtils.writeUTF8(byteBuf, text.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while serializing minecraft metadata " +
                    "OptionalChatType", e);
        }
        return byteBuf;
    }
}
