package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetLocalPlayerAsInitializedPacketPacket implements Packet {


    public SetLocalPlayerAsInitializedPacketPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED_PACKET;
    }
}

