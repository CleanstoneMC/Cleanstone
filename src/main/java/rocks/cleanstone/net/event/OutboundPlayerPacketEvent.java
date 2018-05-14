package rocks.cleanstone.net.event;

import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.player.Player;

public class OutboundPlayerPacketEvent extends OutboundPacketEvent {

    private final Player player;

    public OutboundPlayerPacketEvent(Packet packet, Connection connection, Networking networking, Player player) {
        super(packet, connection, networking);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
