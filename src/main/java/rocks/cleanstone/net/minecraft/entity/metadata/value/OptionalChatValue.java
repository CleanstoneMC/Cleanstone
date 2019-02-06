package rocks.cleanstone.net.minecraft.entity.metadata.value;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.game.chat.message.Text;

@Slf4j
public class OptionalChatValue implements EntityMetadataEntryValue {
    @Nullable
    private final Text text;

    private OptionalChatValue(@Nullable Text text) {
        this.text = text;
    }

    public static OptionalChatValue of(@Nullable Text text) {
        return new OptionalChatValue(text);
    }

    @Override
    public ByteBuf serialize() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeBoolean(text != null);
        if (text != null) {
            ByteBuf chatData = ChatValue.of(text).serialize();
            byteBuf.writeBytes(chatData);
            chatData.release();
        }
        return byteBuf;
    }
}
