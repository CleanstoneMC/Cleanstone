package rocks.cleanstone.net.minecraft.entity.metadata.value;

import com.google.common.base.Preconditions;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.utils.ByteBufUtils;

@Slf4j
public class ChatValue implements EntityMetadataEntryValue {

    private final Text text;

    private ChatValue(Text text) {
        Preconditions.checkNotNull(text, "text cannot be null");
        this.text = text;
    }

    public static ChatValue of(Text text) {
        return new ChatValue(text);
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
