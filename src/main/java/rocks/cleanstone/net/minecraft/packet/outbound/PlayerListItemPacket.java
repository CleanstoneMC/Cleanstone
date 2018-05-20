package rocks.cleanstone.net.minecraft.packet.outbound;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.net.minecraft.login.SessionServerResponse;
import rocks.cleanstone.net.minecraft.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.player.Player;

public class PlayerListItemPacket implements Packet {

    private final Action action;

    private final Set<Player> players = new HashSet<>();

    public PlayerListItemPacket(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PLUGIN_MESSAGE;
    }

    public enum Action {
        ADD_PLAYER(0), UPDATE_GAMEMODE(1), UPDATE_LATENCY(2), UPDATE_DISPLAY_NAME(3), REMOVE_PLAYER(4);

        private final int id;

        Action(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static class PlayerItem {
        private final UUID uuid;
        private String name;
        private SessionServerResponse.Property[] properties;
        private int gameMode, ping;
        private boolean hasDisplayName;
        private Chat displayName;

        public PlayerItem(UUID uuid) {
            this.uuid = uuid;
        }
    }
}
