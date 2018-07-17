package rocks.cleanstone.net.packet.outbound;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.UserProperty;

public class PlayerListItemPacket implements Packet {

    private final Action action;

    private final Collection<PlayerItem> players = new HashSet<>();

    public PlayerListItemPacket(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public Collection<PlayerItem> getPlayers() {
        return players;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.PLAYER_LIST_ITEM;
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
        private final Collection<UserProperty> userProperties;
        private final String name;
        private final GameMode gameMode;
        private final int ping;
        private final Text displayName;

        public PlayerItem(UUID uuid, Collection<UserProperty> userProperties, String name, GameMode gameMode, int ping, Text displayName) {
            this.uuid = uuid;
            this.userProperties = userProperties;
            this.name = name;
            this.gameMode = gameMode;
            this.ping = ping;
            this.displayName = displayName;
        }

        public PlayerItem(Player player) {
            this(player.getID().getUUID(), player.getUserProperties(), player.getID().getName(), player.getGameMode(), player.getPing(),
                    Text.of(player.getID().getName()));
        }

        public UUID getUUID() {
            return uuid;
        }

        public Collection<UserProperty> getUserProperties() {
            return userProperties;
        }

        public String getName() {
            return name;
        }

        public GameMode getGameMode() {
            return gameMode;
        }


        public int getPing() {
            return ping;
        }

        public Text getDisplayName() {
            return displayName;
        }

        public boolean hasDisplayName() {
            return displayName != null;
        }
    }
}
