package rocks.cleanstone.core.player;

import rocks.cleanstone.net.PlayerConnection;

public class OnlinePlayer extends User {

    public OnlinePlayer(PlayerId playerId, PlayerConnection connection) {
        super(playerId);
    }
}
