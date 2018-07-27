package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import io.netty.buffer.ByteBuf;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.outbound.PlayerListItemPacket;
import rocks.cleanstone.net.protocol.PacketCodec;
import rocks.cleanstone.net.utils.ByteBufUtils;
import rocks.cleanstone.player.UserProperty;

import java.io.IOException;
import java.util.Collection;

import static rocks.cleanstone.net.utils.ByteBufUtils.writeUTF8;
import static rocks.cleanstone.net.utils.ByteBufUtils.writeVarInt;

public class PlayerListItemCodec implements PacketCodec {

    @Override
    public Packet decode(ByteBuf byteBuf) {
        throw new UnsupportedOperationException("PlayerListItem is outbound and cannot be decoded");
    }

    @Override
    public ByteBuf encode(ByteBuf byteBuf, Packet packet) throws IOException {
        PlayerListItemPacket playerListItemPacket = (PlayerListItemPacket) packet;
        PlayerListItemPacket.Action action = playerListItemPacket.getAction();
        writeVarInt(byteBuf, action.getId());
        Collection<PlayerListItemPacket.PlayerItem> players = playerListItemPacket.getPlayers();
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

    @Override
    public ByteBuf upgradeByteBuf(ByteBuf previousLayerByteBuf) {
        return previousLayerByteBuf;
    }

    @Override
    public ByteBuf downgradeByteBuf(ByteBuf nextLayerByteBuf) {
        return nextLayerByteBuf;
    }
}
