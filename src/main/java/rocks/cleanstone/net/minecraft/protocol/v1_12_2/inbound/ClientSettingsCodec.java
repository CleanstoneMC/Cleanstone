package rocks.cleanstone.net.minecraft.protocol.v1_12_2.inbound;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.chat.ChatMode;
import rocks.cleanstone.game.inventory.MainHandSide;
import rocks.cleanstone.net.minecraft.packet.enums.DisplayedSkinPart;
import rocks.cleanstone.net.minecraft.packet.inbound.ClientSettingsPacket;
import rocks.cleanstone.net.protocol.InboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

@Slf4j
@Component
public class ClientSettingsCodec implements InboundPacketCodec<ClientSettingsPacket> {
    @Override
    public ClientSettingsPacket decode(ByteBuf byteBuf) throws IOException {

        String localeID = ByteBufUtils.readUTF8(byteBuf, 16);
        Locale locale = new Locale.Builder().setLanguageTag(localeID.replace("_", "-")).build();

        final byte viewDistance = byteBuf.readByte();
        Preconditions.checkArgument(viewDistance >= 2, "viewDistance " + viewDistance + " is too small");

        int modeID = ByteBufUtils.readVarInt(byteBuf);
        ChatMode chatMode = ChatMode.fromModeID(modeID);
        Preconditions.checkNotNull(chatMode, "Invalid chatModeID " + modeID);

        final boolean chatColors = byteBuf.readBoolean();
        final Collection<DisplayedSkinPart> displayedSkinParts = DisplayedSkinPart.fromBitMask(byteBuf.readByte());

        int handID = ByteBufUtils.readVarInt(byteBuf);
        MainHandSide mainHandSide = MainHandSide.fromHandID(handID);
        Preconditions.checkNotNull(mainHandSide, "Invalid mainHandID " + handID);

        return new ClientSettingsPacket(locale, viewDistance, chatMode, chatColors, displayedSkinParts, mainHandSide);
    }
}
