package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.minecraft.packet.outbound.PlayerListItemPacket;
import rocks.cleanstone.net.protocol.Codec;
import rocks.cleanstone.net.protocol.OutboundPacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.player.UserProperty;

import java.io.IOException;
import java.util.Collection;

import static rocks.cleanstone.net.utils.ByteBufUtils.writeUTF8;
import static rocks.cleanstone.net.utils.ByteBufUtils.writeVarInt;

@Codec
public class PlayerListItemCodec implements OutboundPacketCodec<PlayerListItemPacket> {

    @Override
    public ByteBuf encode(ByteBuf byteBuf, PlayerListItemPacket packet) throws IOException {
        PlayerListItemPacket.Action action = packet.getAction();
        writeVarInt(byteBuf, action.getId());
        Collection<PlayerListItemPacket.PlayerItem> players = packet.getPlayers();
        writeVarInt(byteBuf, players.size());

        for (PlayerListItemPacket.PlayerItem playerItem : players) {
            writePlayerItem(byteBuf, playerItem, action);
        }

        return byteBuf;
    }

    private void writePlayerItem(ByteBuf byteBuf, PlayerListItemPacket.PlayerItem playerItem,
                                 PlayerListItemPacket.Action action) throws IOException {
        ByteBufUtils.writeUUID(byteBuf, playerItem.getUUID());
        switch (action) {
            case ADD_PLAYER:
                writeUTF8(byteBuf, playerItem.getName());
                writeVarInt(byteBuf, playerItem.getUserProperties().size());
                for (UserProperty userProperty : playerItem.getUserProperties()) {
                    writeUserProperty(byteBuf, userProperty);
                }
                writeVarInt(byteBuf, playerItem.getGameMode() != null
                        ? playerItem.getGameMode().getTypeId() : 1);
                writeVarInt(byteBuf, playerItem.getPing());
                writeDisplayName(byteBuf, playerItem);
                break;
            case UPDATE_GAMEMODE:
                writeVarInt(byteBuf, playerItem.getGameMode().getTypeId());
                break;
            case UPDATE_LATENCY:
                writeVarInt(byteBuf, playerItem.getPing());
                break;
            case UPDATE_DISPLAY_NAME:
                writeDisplayName(byteBuf, playerItem);
                break;
            case REMOVE_PLAYER:
                break;
        }
    }

    private void writeUserProperty(ByteBuf byteBuf, UserProperty userProperty) throws IOException {
        writeUTF8(byteBuf, userProperty.getName());
        writeUTF8(byteBuf, userProperty.getValue());
        byteBuf.writeBoolean(userProperty.hasSignature());
        if (userProperty.hasSignature()) {
            writeUTF8(byteBuf, userProperty.getSignature());
        }
    }

    private void writeDisplayName(ByteBuf byteBuf, PlayerListItemPacket.PlayerItem playerItem)
            throws IOException {
        byteBuf.writeBoolean(playerItem.hasDisplayName());
        if (playerItem.hasDisplayName()) {
            writeUTF8(byteBuf, playerItem.getDisplayName().toString());
        }
    }
}
