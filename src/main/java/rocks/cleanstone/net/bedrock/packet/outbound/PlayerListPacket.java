package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.PlayerRecords;
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
        return BedrockOutboundPacketType.PLAYER_LIST;
    }
}

