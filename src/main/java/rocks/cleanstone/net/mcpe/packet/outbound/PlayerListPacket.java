package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.PlayerRecords;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PlayerListPacket implements Packet {

    private final PlayerRecords records;

    public PlayerListPacket(PlayerRecords records) {
        this.records = records;
    }

    public PlayerRecords getRecords() {
        return records;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.PLAYER_LIST;
    }
}

