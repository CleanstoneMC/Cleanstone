package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ShowStoreOfferPacket implements Packet {

    private final String unknown0;
    private final boolean unknown1;

    public ShowStoreOfferPacket(String unknown0, boolean unknown1) {
        this.unknown0 = unknown0;
        this.unknown1 = unknown1;
    }

    public String getUnknown0() {
        return unknown0;
    }

    public boolean getUnknown1() {
        return unknown1;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SHOW_STORE_OFFER;
    }
}

